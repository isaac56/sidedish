package team16.sidedish.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team16.sidedish.domain.entity.lookUp.Badge;
import team16.sidedish.domain.entity.lookUp.Category;
import team16.sidedish.domain.entity.lookUp.DeliveryType;
import team16.sidedish.exception.NotFoundException;
import team16.sidedish.repository.lookup.BadgeRepository;
import team16.sidedish.repository.lookup.CategoryRepository;
import team16.sidedish.repository.lookup.DeliveryTypeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LookUpTableUtils {
    private final DeliveryTypeRepository deliveryTypeRepository;
    private final BadgeRepository badgeRepository;
    private final CategoryRepository categoryRepository;

    private static Map<Integer, DeliveryType> deliveryTypeById = new HashMap<>();
    private static Map<Integer, Badge> badgeById = new HashMap<>();
    private static Map<Integer, Category> categoryById = new HashMap<>();

    @Autowired
    public LookUpTableUtils(DeliveryTypeRepository deliveryTypeRepository
            , BadgeRepository badgeRepository
            , CategoryRepository categoryRepository) {
        this.deliveryTypeRepository = deliveryTypeRepository;
        this.badgeRepository = badgeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Scheduled(initialDelay = 0, fixedRate = 1000 * 3600)
    private void refresh() {
        deliveryTypeById = deliveryTypeRepository.findAll().stream()
                .collect(Collectors.toMap(DeliveryType::getId, Function.identity()));
        badgeById = badgeRepository.findAll().stream()
                .collect(Collectors.toMap(Badge::getId, Function.identity()));
        categoryById = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));
    }

    public static DeliveryType getDeliveryTypeById(Integer deliveryTypeId) {
        DeliveryType deliveryType = deliveryTypeById.getOrDefault(deliveryTypeId, null);
        if (deliveryType == null) {
            throw new NotFoundException(deliveryTypeId + "번 delivery type 은 존재하지 않습니다.");
        }
        return deliveryType;
    }

    public static Badge getBadgeById(Integer badgeId) {
        Badge badge = badgeById.getOrDefault(badgeId, null);
        if (badge == null) {
            throw new NotFoundException(badgeId + "번 badge 는 존재하지 않습니다.");
        }
        return badge;
    }

    public static Category getCategoryById(Integer categoryId) {
        Category category = categoryById.getOrDefault(categoryId, null);
        if (category == null) {
            throw new NotFoundException(categoryId + "번 category 는 존재하지 않습니다.");
        }
        return category;
    }
}
