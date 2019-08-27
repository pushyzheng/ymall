package site.pushy.ymall.service;

import site.pushy.ymall.pojo.dto.UserDTO;

public interface AuthService {

    UserDTO login(String username, String password);

    void register();

    UserDTO getUserByToken(String token);

    int logout(String token);

}
