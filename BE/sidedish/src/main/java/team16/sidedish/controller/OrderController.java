package team16.sidedish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team16.sidedish.dto.GithubEmailDTO;
import team16.sidedish.dto.request.OrderRequestDto;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.exception.NotAuthorizedException;
import team16.sidedish.service.LoginService;
import team16.sidedish.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;
    private final LoginService loginService;

    @Autowired
    public OrderController(OrderService orderService, LoginService loginService) {
        this.orderService = orderService;
        this.loginService = loginService;
    }

    @PostMapping
    public ApiResult<String> createOrder(@RequestBody OrderRequestDto orderRequestDto, @RequestHeader(name = "Authorization") String accessToken) {
        logger.debug("orderRequestDto가 널? {}", orderRequestDto == null);
        if (accessToken == null) {
            throw new NotAuthorizedException();
        }
        GithubEmailDTO githubEmailDTO = loginService.getEmailFromGithub(accessToken);
        if (githubEmailDTO == null) {
            throw new NotAuthorizedException();
        }

        orderService.makeOrder(orderRequestDto.getProductHash(), orderRequestDto.getCount(), githubEmailDTO.getEmail());

        return ApiResult.succeed("OK");
    }
}
