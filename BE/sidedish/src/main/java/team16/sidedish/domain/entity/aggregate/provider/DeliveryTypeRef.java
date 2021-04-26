package team16.sidedish.domain.entity.aggregate.provider;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"deliveryTypeId"})
@Table("provider_has_delivery_type")
public class DeliveryTypeRef {
    private Integer deliveryTypeId;

    public DeliveryTypeRef(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }
}
