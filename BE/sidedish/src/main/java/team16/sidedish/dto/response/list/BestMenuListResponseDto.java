package team16.sidedish.dto.response.list;

import lombok.*;
import team16.sidedish.dto.response.BestMenuResponseDto;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestMenuListResponseDto {
    private List<BestMenuResponseDto> body;

    public static BestMenuListResponseDto of(List<BestMenuResponseDto> bestMenuResponseDtos) {
        return BestMenuListResponseDto.builder()
                .body(bestMenuResponseDtos)
                .build();
    }
}
