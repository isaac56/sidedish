package team16.sidedish.domain.entity.aggregate.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

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
            this.orders.add(order);
        }
    }

    public void removeOrder(Order order) {
        this.orders.remove(order.getId());
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
