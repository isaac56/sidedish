package team16.sidedish.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team16.sidedish.domain.entity.aggregate.product.Product;
import team16.sidedish.domain.entity.aggregate.provider.Provider;

import java.util.List;
import java.util.stream.Collectors;

import static team16.sidedish.utils.LookUpTableUtils.getBadgeById;
import static team16.sidedish.utils.LookUpTableUtils.getDeliveryTypeById;

/**
 * {
 * "detail_hash": "H9881",
 * "image": "https://cdn.bmf.kr/_data/product/H9881/f2a7b4df359c850b1a9eb57e17ddf6fc.jpg",
 * "alt": "[소중한식사] 경상도 한상차림",
 * "delivery_type": [
 * "새벽배송",
 * "전국택배"
 * ],
 * "title": "[소중한식사] 경상도 한상차림",
 * "description": "경상도 명물 요리 세 가지를 한 상에!",
 * "n_price": "39,000",
 * "s_price": "31,200원",
 * "badge": [
 * "이벤트특가"
 * ]
 * }
 */

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuResponseDto {
    private String detailHash;
    private String image; // url
    private String alt; // [provider_name] + product_name
    private List<String> deliveryType;
    private String title; // same as alt
    private String description; // description
    private String nPrice; // price_original
    private String sPrice; // price_discount
    private List<String> badge;
    private int stock;


    /**
     * @param product
     * @param provider
     * @return MenuResponseDto
     */
    public static MenuResponseDto of(Product product, Provider provider) {
        List<String> deliveryTypeNames = provider.getDeliveryTypeRefs().stream().
                map(x -> getDeliveryTypeById(x.getDeliveryTypeId()).getName()).collect(Collectors.toList());

        List<String> badgeNames = product.getBadgeRefs().stream().
                map(x -> getBadgeById(x.getBadgeId()).getName()).collect(Collectors.toList());

        return MenuResponseDto.builder()
                .detailHash(product.getHash())
                .image(product.getTopImageUrl())
                .alt("[" + provider.getName() + "] " + product.getName())
                .deliveryType(deliveryTypeNames)
                .title("[" + provider.getName() + "] " + product.getName())
                .description(product.getDescription())
                .nPrice(product.getPriceOriginal() + "")
                .sPrice(product.getPriceDiscount() + "원")
                .badge(badgeNames)
                .stock(product.getStock())
                .build();
    }
}
