package site.pushy.ymall.service;

import site.pushy.ymall.pojo.dto.Seckill;
import site.pushy.ymall.pojo.dto.SeckillDTO;
import site.pushy.ymall.pojo.dto.SeckillOrderInfo;
import site.pushy.ymall.pojo.dto.SeckillTask;

import java.util.List;

public interface SeckillService {

    Seckill isSeckillGood(Long itemId);

    List<SeckillDTO> listSeckill();

    SeckillTask getSeckillTask(Long userId, Long itemId);

    void saveOrder(String secKillId, SeckillOrderInfo orderInfo);

    void publishSeckill(Seckill seckill);

}
