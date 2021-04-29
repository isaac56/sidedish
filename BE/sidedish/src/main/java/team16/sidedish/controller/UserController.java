package team16.sidedish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/login/oauth2/code/github")
    public ApiResult<String> login_GithubOauth(@PathParam("code") String code, HttpSession httpSession) {
        logger.debug("github authorization code: {}", code);

        GithubTokenDTO accessToken = loginService.getAccessToken(code);
        logger.debug("github access token: {}", accessToken.getAccessToken());
        GithubEmailDTO githubEmailDTO = loginService.getEmailFromGithub(accessToken.getAccessToken());

        //원래는 회원가입이 되어있지 않아서 로그인 실패가 되어야 하지만, 이 프로젝트에서는 자동 회원가입 시켜줌
        if (userService.emailExist(githubEmailDTO.getEmail())) {
            // throw new NotFoundException(githubEmail.getEmail() +" 사용자는 존재하지 않습니다.");
            userService.createUser(githubEmailDTO.getEmail());
        }

        HttpSessionUtils.setAccessToken(httpSession, accessToken.getAccessToken());

        return ApiResult.succeed("OK");
    }

    @GetMapping("/valid")
    public ApiResult<Boolean> getValid(HttpSession httpSession) {
        logger.debug("로그인 되어있는지 확인 요청");
        String accessToken = HttpSessionUtils.getAccessToken(httpSession);
        if (accessToken == null) {
            return ApiResult.succeed(false);
        }

        GithubEmailDTO githubEmailDTO = loginService.getEmailFromGithub(accessToken);
        if (githubEmailDTO == null) {
            return ApiResult.succeed(false);
        }
        return ApiResult.succeed(true);
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession) {
        HttpSessionUtils.removeAccessToken(httpSession);
    }
}
