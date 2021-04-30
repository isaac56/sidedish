package team16.sidedish.domain.entity.aggregate.provider;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name"})
public class Provider {
    @Id
    private Long id;

    private String name;

    private Integer deliveryFee;

    private Integer deliveryFreePrice;

    @MappedCollection(idColumn = "provider_id")
    private Set<DeliveryTypeRef> deliveryTypeRefs = new HashSet<>();

    public Provider(String name, Integer deliveryFee, Integer deliveryFreePrice) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.deliveryFreePrice = deliveryFreePrice;
    }

    public void addDeliveryTypeRef(DeliveryTypeRef... deliveryTypeRefs) {
        for (DeliveryTypeRef deliveryTypeRef : deliveryTypeRefs) {
            this.deliveryTypeRefs.add(deliveryTypeRef);
        }
    }

    public void removeDeliveryTypeRef(DeliveryTypeRef deliveryTypeRef) {
        this.deliveryTypeRefs.remove(deliveryTypeRef);
    }
}
