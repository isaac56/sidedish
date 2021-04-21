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
    private Set<ProviderDeliveryType> deliveryTypes = new HashSet<>();

    public Provider(String name, Integer deliveryFee, Integer deliveryFreePrice) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.deliveryFreePrice = deliveryFreePrice;
    }

    public void addDeliveryType(ProviderDeliveryType... providerDeliveryTypes) {
        for (ProviderDeliveryType providerDeliveryType : providerDeliveryTypes) {
            providerDeliveryType.setProviderId(this.id);
            this.deliveryTypes.add(providerDeliveryType);
        }
    }

    public void removeDeliveryType(ProviderDeliveryType providerDeliveryType) {
        this.deliveryTypes.remove(providerDeliveryType);
    }
}
