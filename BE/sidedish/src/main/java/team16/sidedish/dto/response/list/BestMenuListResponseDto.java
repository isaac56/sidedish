package team16.sidedish.dto.response.list;

import lombok.*;
import team16.sidedish.dto.response.BestMenuResponseDto;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestMenuListResponseDto {
    private Set<BestMenuResponseDto> body;

    public static BestMenuListResponseDto of() {
        return BestMenuListResponseDto.builder()
//                .body()
                .build();
    }
}
