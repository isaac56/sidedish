package team16.sidedish.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * {
 *      "detail_hash": "H9881",
 *      "image": "https://cdn.bmf.kr/_data/product/H9881/f2a7b4df359c850b1a9eb57e17ddf6fc.jpg",
 *      "alt": "[소중한식사] 경상도 한상차림",
 *      "delivery_type": [
 *          "새벽배송",
 *          "전국택배"
 *      ],
 *      "title": "[소중한식사] 경상도 한상차림",
 *      "description": "경상도 명물 요리 세 가지를 한 상에!",
 *      "n_price": "39,000",
 *      "s_price": "31,200원",
 *      "badge": [
 *          "이벤트특가"
 *      ]
 * }
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuResponseDto {
    private String detailHash;
    private String image; // url
    private String alt;
    private List<String> deliveryType;
    private String title;
    private String description;
    private String nPrice;
    private String sPrice;
    private List<String> badge;
}
