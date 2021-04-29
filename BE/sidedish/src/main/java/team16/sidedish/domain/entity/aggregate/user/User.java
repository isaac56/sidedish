package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"email"})
public class User {
    @Id
    private Long id;

    private String email;

    @MappedCollection(idColumn = "user_id", keyColumn = "hash")
    private Map<String, Order> orders = new HashMap<>();

    @MappedCollection(idColumn = "user_id")
    private Set<Cart> carts = new HashSet<>();

    public User(String email) {
        this.email = email;
    }

    public Optional<Order> getOrder(long orderId) {
        Order order = this.orders.getOrDefault(orderId, null);
        return Optional.ofNullable(order);
    }

    public void addOrder(Order... orders) {
        for (Order order : orders) {
            String hash = this.id + "_" + (this.orders.size() + 1);
            this.orders.put(hash, order);
        }
    }

    public void removeOrder(Order order) {
        this.orders.remove(order.getId());
    }

    public void addCart(Cart... carts) {
        for (Cart cart : carts) {
            this.carts.add(cart);
        }
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
    }
}
