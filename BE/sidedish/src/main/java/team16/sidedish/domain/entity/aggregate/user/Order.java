package team16.sidedish.domain.entity.aggregate.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {
    @Id
    private Long id;

    private Long userId;

    private LocalDateTime createTime;

    @MappedCollection(idColumn = "order_id")
    private final Set<OrderProduct> products = new HashSet<>();

    public Order(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void addProduct(OrderProduct... orderProducts){
        for(OrderProduct orderProduct : orderProducts){
            this.products.add(orderProduct);
        }
    }

    public void removeProduct(OrderProduct orderProduct){
        this.products.remove(orderProduct);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
