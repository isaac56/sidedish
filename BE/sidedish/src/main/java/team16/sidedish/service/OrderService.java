package team16.sidedish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.user.Order;
import team16.sidedish.domain.entity.aggregate.user.OrderProduct;
import team16.sidedish.domain.entity.aggregate.user.User;
import team16.sidedish.exception.NotFoundException;
import team16.sidedish.repository.ProductRepository;
import team16.sidedish.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void makeOrder(String productHash, int count, long userId) {
        logger.debug("{}번 사용자가 {} 제품의 {}개 주문 요청", userId, productHash, count);
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException(userId + "번 사용자는 존재하지 않습니다."));
        Product product = productRepository.findByHash(productHash)
                .orElseThrow(() -> new NotFoundException(productHash + "의 제품은 존재하지 않습니다."));

        if (product.getStock() < count) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        product.setStock(product.getStock() - count);
        productRepository.save(product);
        logger.debug("product 재고 저장");

        Order order = new Order();
        order.addProduct(new OrderProduct(product.getId(), count));

        user.addOrder(order);
        userRepository.save(user);
        logger.debug("user에 주문 저장");
    }

    public List<Order> getOrderSet(long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException(userId + "번 사용자는 존재하지 않습니다."));

        return user.getOrders().values().stream().collect(Collectors.toList());
    }

    public Order getOrder(long orderId, long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NotFoundException(userId + "번 사용자는 존재하지 않습니다."));

        return user.getOrder(orderId).orElseThrow(() -> new NotFoundException());
    }
}
