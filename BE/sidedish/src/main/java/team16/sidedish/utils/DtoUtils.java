package team16.sidedish.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.product.ProductBadge;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.aggregate.provider.ProviderDeliveryType;
import team16.sidedish.domain.entity.lookUp.Badge;
import team16.sidedish.domain.entity.lookUp.DeliveryType;
import team16.sidedish.repository.lookup.BadgeRepository;
import team16.sidedish.repository.lookup.DeliveryTypeRepository;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class DtoUtils {

    private static DeliveryTypeRepository deliveryTypeRepository;
    private static BadgeRepository badgeRepository;

    private static Map<Integer, String> deliveryTypeIdName = new HashMap<>();
    private static Map<Integer, String> badgeIdName = new HashMap<>();

    @Autowired
    public DtoUtils(DeliveryTypeRepository deliveryTypeRepository
            , BadgeRepository badgeRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
        this.badgeRepository = badgeRepository;
    }

    @Scheduled(initialDelay = 0, fixedRate = 1000 * 3600)
    private void refreshDeliveryType() {
        deliveryTypeIdName = deliveryTypeRepository.findAll().stream()
                .collect(Collectors.toMap(DeliveryType::getId, DeliveryType::getName));
    }

    private static String getDeliveryTypeNameById(Integer deliveryTypeId) {
        return deliveryTypeIdName.getOrDefault(deliveryTypeId, null);
    }

    public static List<String> getDeliveryTypeNamesFrom(Provider provider) {
        Set<ProviderDeliveryType> providerDeliveryTypes = provider.getDeliveryTypes();
        List<String> deliveryTypeNames = new ArrayList<>();
        for (ProviderDeliveryType providerDeliveryType : providerDeliveryTypes) {
            String deliveryTypeName = getDeliveryTypeNameById(providerDeliveryType.getDeliveryTypeId());
            deliveryTypeNames.add(deliveryTypeName);
        }
        return deliveryTypeNames;
    }

    @Scheduled(initialDelay = 0, fixedRate = 1000 * 3600)
    private void refreshBadge() {
        badgeIdName = badgeRepository.findAll().stream()
                .collect(Collectors.toMap(Badge::getId, Badge::getName));
    }

    private static String getBadgeNameById(Integer badgeId) {
        return badgeIdName.getOrDefault(badgeId, null);
    }

    public static List<String> getBadgeNamesFrom(Product product) {
        Set<ProductBadge> productBadges = product.getProductBadges();
        List<String> badgeNames = new ArrayList<>();
        for (ProductBadge productBadge : productBadges) {
            String badgeName = getBadgeNameById(productBadge.getBadgeId());
            badgeNames.add(badgeName);
        }
        return badgeNames;
    }
}
