package team16.sidedish.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderRequestDto {
    private String productHash;
    private Integer count;
}
