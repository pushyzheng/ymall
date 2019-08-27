package site.pushy.ymall.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.pushy.ymall.common.util.RespUtil;
import site.pushy.ymall.pojo.dto.LoginRegister;
import site.pushy.ymall.pojo.dto.UserDTO;
import site.pushy.ymall.service.AuthService;

@RestController
@Api(tags = "认证相关接口")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/member/checkLogin")
    @ApiOperation("检查是否登录")
    public String checkLogin(@RequestParam(required = false) String token) {
        UserDTO userDTO = authService.getUserByToken(token);
        return RespUtil.success(userDTO);
    }

    @PostMapping("/member/login")
    @ApiOperation("登录")
    public String login(@RequestBody LoginRegister body) {
        UserDTO userDTO = authService.login(body.getUserName(), body.getUserPwd());
        return RespUtil.success(userDTO);
    }

    @GetMapping("/member/loginOut")
    @ApiOperation("注销")
    public String logout(@RequestParam(defaultValue = "") String token) {
        authService.logout(token);
        return RespUtil.success("成功注销");
    }

}
