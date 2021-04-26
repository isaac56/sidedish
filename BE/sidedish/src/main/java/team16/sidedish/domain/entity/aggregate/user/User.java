package team16.sidedish.domain.entity.aggregate.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"email"})
public class User {
    @Id
    private Long id;

    private String email;

    private String password;

    @MappedCollection(idColumn = "user_id")
    private Set<Order> orders = new HashSet<>();

    @MappedCollection(idColumn = "user_id")
    private Set<Cart> carts = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public void addOrder(Order... orders) {
        for (Order order : orders) {
            order.setUserId(this.id);
            this.orders.add(order);
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
