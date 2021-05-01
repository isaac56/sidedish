package team16.sidedish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team16.sidedish.dto.GithubEmailDTO;
import team16.sidedish.dto.GithubTokenDTO;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.service.LoginService;
import team16.sidedish.service.UserService;

import java.util.Map;

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

    @PostMapping("/login/oauth2/code/github")
    public ApiResult<String> login_GithubOauth(@RequestBody Map<String, String> payload) {
        String authorization = payload.get("authorization");
        logger.debug("github authorization code: {}", authorization);

        GithubTokenDTO accessToken = loginService.getAccessToken(authorization);
        logger.debug("github access token: {}", accessToken.getAccessToken());
        GithubEmailDTO githubEmailDTO = loginService.getEmailFromGithub(accessToken.getAccessToken());

        //원래는 회원가입이 되어있지 않아서 로그인 실패가 되어야 하지만, 이 프로젝트에서는 자동 회원가입 시켜줌
        if (!userService.emailExist(githubEmailDTO.getEmail())) {
            // throw new NotFoundException(githubEmail.getEmail() +" 사용자는 존재하지 않습니다.");
            userService.createUser(githubEmailDTO.getEmail());
        }
        return ApiResult.succeed(accessToken.getAccessToken());
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
}
