package team16.sidedish.domain.entity.aggregate.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    private Long id;

    private Long userId;

    private LocalDateTime createTime;

    @MappedCollection(idColumn = "order_id")
    private Set<OrderProduct> products = new HashSet<>();

    public Order(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Set<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderProduct> products) {
        this.products = products;
    }

    public void addProduct(OrderProduct... orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrderId(this.id);
            this.products.add(orderProduct);
        }
    }

    public void removeProduct(OrderProduct orderProduct) {
        this.products.remove(orderProduct);
    }
}
