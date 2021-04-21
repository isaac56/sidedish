package team16.sidedish.domain.entity.aggregate.provider;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

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

    public Set<ProviderDeliveryType> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(Set<ProviderDeliveryType> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public void addDeliveryType(ProviderDeliveryType... providerDeliveryTypes) {
        for (ProviderDeliveryType providerDeliveryType : providerDeliveryTypes) {
            this.deliveryTypes.add(providerDeliveryType);
        }
    }

    public void removeDeliveryType(ProviderDeliveryType providerDeliveryType) {
        this.deliveryTypes.remove(providerDeliveryType);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Integer deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Integer getDeliveryFreePrice() {
        return deliveryFreePrice;
    }

    public void setDeliveryFreePrice(Integer deliveryFreePrice) {
        this.deliveryFreePrice = deliveryFreePrice;
    }
}
