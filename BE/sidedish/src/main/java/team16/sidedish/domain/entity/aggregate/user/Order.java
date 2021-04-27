package team16.sidedish.domain.entity.aggregate.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Order {
    @Id
    private Long id;

    private LocalDateTime createTime;

    @MappedCollection(idColumn = "order_id")
    private Set<OrderProduct> products = new HashSet<>();

    public Order() {
        this.createTime = LocalDateTime.now();
    }

    public void addProduct(OrderProduct... orderProducts) {
        for (OrderProduct orderProduct : orderProducts) {
            this.products.add(orderProduct);
        }
    }

    public void removeProduct(OrderProduct orderProduct) {
        this.products.remove(orderProduct);
    }
}
