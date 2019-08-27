package site.pushy.ymall.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.pushy.ymall.common.jedis.JedisClient;
import site.pushy.ymall.dao.TbUserMapper;
import site.pushy.ymall.pojo.DO.TbUser;
import site.pushy.ymall.pojo.DO.TbUserExample;
import site.pushy.ymall.pojo.dto.UserDTO;
import site.pushy.ymall.service.AuthService;

import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JedisClient redis;

    @Autowired
    private TbUserMapper userMapper;

    private static final int SESSION_EXPIRE = 1800;  // 30分钟

    @Override
    public UserDTO login(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            throw new RuntimeException("用户名或密码错误");
        }
        TbUser user = list.get(0);
        String token = UUID.randomUUID().toString().replace("-", "");

        UserDTO userDTO = new UserDTO(user);
        userDTO.setState(1);
        userDTO.setToken(token);

        redis.set("SESSION:" + token, JSON.toJSONString(userDTO));
        redis.expire("SESSION:" + token, SESSION_EXPIRE);
        return userDTO;
    }

    @Override
    public void register() {

    }

    @Override
    public UserDTO getUserByToken(String token) {
        String json = redis.get("SESSION:" + token);
        if (json == null) {
            UserDTO user = new UserDTO();
            user.setState(0);
            return user;
        }
        // 重置过期时间
        redis.expire("SESSION:" + token, SESSION_EXPIRE);
        return JSON.parseObject(json, UserDTO.class);
    }

    @Override
    public int logout(String token) {
        redis.del("SESSION:" + token);
        return 1;
    }
}
