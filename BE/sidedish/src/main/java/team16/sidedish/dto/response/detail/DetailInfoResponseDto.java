package team16.sidedish.dto.response.detail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DetailInfoResponseDto {
    private String topImage;
    private List<String> thumbImages;
    private String productDescription;
    private int point;
    private String deliveryInfo;
    private String deliveryFee;
    private List<String> prices;
    private List<String> detailSection;
}
