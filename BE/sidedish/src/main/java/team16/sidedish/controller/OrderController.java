package team16.sidedish.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.dto.request.OrderRequestDto;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.service.OrderService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResult<String> createOrder(@RequestBody OrderRequestDto orderRequestDto, HttpSession httpSession) {
        logger.debug("orderRequestDto가 널? {}", orderRequestDto == null);
        orderService.makeOrder(orderRequestDto.getProductHash(), orderRequestDto.getCount(), 1L);

        return ApiResult.succeed("OK");
    }
}
