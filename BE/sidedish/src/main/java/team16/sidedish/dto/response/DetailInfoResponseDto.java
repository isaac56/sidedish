package team16.sidedish.dto.response.detail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.DeliveryTypeRef;
import team16.sidedish.domain.entity.aggregate.provider.Provider;
import team16.sidedish.domain.entity.lookUp.DeliveryType;
import team16.sidedish.utils.LookUpTableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailInfoResponseDto {
    private String topImage;
    private List<String> thumbImages;
    private String productDescription;
    private int point;
    private String deliveryInfo;
    private String deliveryFee;
    private List<String> prices;
    private List<String> detailSection;

    public static DetailInfoResponseDto of(Product product, Provider provider) {
        List<String> thumbImages = product.getImages().stream().filter(x -> !x.isDetail()).
                map(x -> x.getUrl()).collect(Collectors.toList());

        List<String> detailSection = product.getImages().stream().filter(x -> x.isDetail()).
                map(x -> x.getUrl()).collect(Collectors.toList());

        String deliveryFee = getDeliveryFee(provider.getDeliveryFee(), provider.getDeliveryFreePrice());
        String deliveryInfo = getDeliveryInfo(provider.getDeliveryTypeRefs());

        List<String> prices = new ArrayList<>();
        prices.add(Integer.toString(product.getPriceOriginal()));
        prices.add(Integer.toString(product.getPriceDiscount()));

        return builder()
                .topImage(product.getTopImageUrl())
                .thumbImages(thumbImages)
                .productDescription(product.getDescription())
                .point(product.getPoint())
                .deliveryInfo(deliveryInfo)
                .deliveryFee(deliveryFee)
                .prices(prices)
                .detailSection(detailSection)
                .build();
    }

    private static String getDeliveryFee(int deliveryFee, int deliveryFreePrice) {
        return String.format("%d원 (%d원 이상 구매 시 무료)", deliveryFee, deliveryFreePrice);
    }

    private static String getDeliveryInfo(Set<DeliveryTypeRef> deliveryTypeRefs) {
        StringBuilder builder = new StringBuilder();
        for (DeliveryTypeRef deliveryTypeRef : deliveryTypeRefs) {
            DeliveryType deliveryType = LookUpTableUtils.getDeliveryTypeById(deliveryTypeRef.getDeliveryTypeId());
            builder.append(deliveryType.getMessage());
            builder.append(" / ");
        }
        builder.append("[화 · 수 · 목 · 금 · 토] 수령 가능한 상품입니다.");
        return builder.toString();
    }
}
