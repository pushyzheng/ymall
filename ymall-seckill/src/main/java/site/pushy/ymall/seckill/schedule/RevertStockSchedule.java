package site.pushy.ymall.seckill.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import site.pushy.ymall.dao.TbItemMapper;
import site.pushy.ymall.dao.TbOrderItemMapper;
import site.pushy.ymall.dao.TbOrderMapper;
import site.pushy.ymall.pojo.DO.TbItem;
import site.pushy.ymall.pojo.DO.TbOrder;
import site.pushy.ymall.pojo.DO.TbOrderItem;
import site.pushy.ymall.pojo.DO.TbOrderItemExample;

import java.util.List;

@Component
public class RevertStockSchedule {

    private static final String SCHEDULE_CRON = "0 0 0 * * ?";  // 每天凌晨执行

    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbOrderItemMapper orderItemMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Scheduled(cron = SCHEDULE_CRON)
    public void validOrder() {
        List<TbOrder> orders = orderMapper.selectByExample(null);
        for (TbOrder order : orders) {
            if (order.getStatus() == 0) {
                long diff = System.currentTimeMillis() - order.getCreateTime().getTime();
                long days = diff / (1000 * 60 * 60 * 24);
                if (days >= 1) {  // 大于1天，使订单失效
                    transactionTemplate.execute(status -> {  // 执行事务
                        order.setStatus(5);
                        boolean revertResult = revertItemNum(order);
                        if (!(revertResult && orderMapper.updateByPrimaryKey(order) == 1)) {
                            // 如果订单项目库存与订单状态未更新成功，则回滚数据
                            status.setRollbackOnly();
                        }
                        return null;
                    });
                }
            }
        }
    }

    /**
     * 归还商品的库存
     * @param order
     */
    private boolean revertItemNum(TbOrder order) {
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(order.getOrderId());
        List<TbOrderItem> orderItems = orderItemMapper.selectByExample(example);

        for (TbOrderItem orderItem : orderItems) {
            TbItem item = itemMapper.selectByPrimaryKey(Long.valueOf(orderItem.getItemId()));
            item.setNum(item.getNum() + orderItem.getNum());
            if (itemMapper.updateByPrimaryKey(item) != 1) {
                return false;
            }
        }
        return true;
    }
}
