package team16.sidedish.domain.entity.aggregate.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

public class Cart {
    @Id
    private Long id;

    private Long user_id;

    private Long product_id;

    private Integer count;

    public Cart(Long product_id, Integer count) {
        this.product_id = product_id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(getUser_id(), cart.getUser_id()) && Objects.equals(getProduct_id(), cart.getProduct_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getProduct_id());
    }
}
