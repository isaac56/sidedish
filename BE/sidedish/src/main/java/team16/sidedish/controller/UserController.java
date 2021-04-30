package team16.sidedish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import team16.sidedish.dto.GithubEmailDTO;
import team16.sidedish.dto.GithubTokenDTO;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.service.LoginService;
import team16.sidedish.service.UserService;
import team16.sidedish.utils.HttpSessionUtils;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LoginService loginService;
    private final UserService userService;

    @Autowired
    public UserController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/valid")
    public ApiResult<Boolean> getValid(@RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("로그인 되어있는지 확인 요청");
        if (accessToken == null) {
            return ApiResult.succeed(false);
        }

        GithubEmailDTO githubEmailDTO = loginService.getEmailFromGithub(accessToken);
        if (githubEmailDTO == null) {
            return ApiResult.succeed(false);
        }

        if (!userService.emailExist(githubEmailDTO.getEmail())) {
            return ApiResult.succeed(false);
        }
        return ApiResult.succeed(true);
    }

    @PostMapping("/logout")
    public ApiResult<String> logout(HttpSession httpSession) {
        HttpSessionUtils.removeAccessToken(httpSession);

        return ApiResult.succeed("OK");
    }
}
