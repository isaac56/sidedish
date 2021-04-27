package team16.sidedish.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team16.sidedish.domain.entity.aggregate.user.Cart;
import team16.sidedish.domain.entity.aggregate.user.Order;
import team16.sidedish.domain.entity.aggregate.user.OrderProduct;
import team16.sidedish.domain.entity.aggregate.user.User;

@Transactional
@SpringBootTest
class UserRepositoryTest {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @DisplayName("user save 테스트")
    public void save() {
        User user = new User("isaac56@naver.com", "test");
        user = userRepository.save(user);

        user.addCart(new Cart(1L, 1));

        Order order = new Order();
        order.addProduct(new OrderProduct(1L, 1));
        user.addOrder(order);

        user = userRepository.save(user);

        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(user.getCarts().size()).isEqualTo(1);
        Assertions.assertThat(user.getOrders().size()).isEqualTo(1);
    }
}
