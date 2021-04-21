package team16.sidedish.domain.entity.aggregate.provider;

import org.springframework.data.annotation.Id;

public class ProviderDeliveryType{
    @Id
    private Long id;

    private Long providerId;

    private Integer deliveryType;

    public ProviderDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Long getId() {
        return id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }
}
