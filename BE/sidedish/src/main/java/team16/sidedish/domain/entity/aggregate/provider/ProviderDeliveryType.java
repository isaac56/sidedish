package team16.sidedish.domain.entity.aggregate.provider;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"providerId", "deliveryTypeId"})
@Table("provider_has_delivery_type")
public class ProviderDeliveryType {
    @Id
    private Long id;

    private Long providerId;

    private Integer deliveryTypeId;

    public ProviderDeliveryType(Integer deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }
}
