package team16.sidedish.dto.response.list;

import lombok.*;
import team16.sidedish.dto.response.MenuResponseDto;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuListResponseDto {
    private List<MenuResponseDto> body;

    /**
     *
     * @param menuResponseDtos
     * @return MenuListResponseDto
     */
    public static MenuListResponseDto of(List<MenuResponseDto> menuResponseDtos) {
        return MenuListResponseDto.builder()
                .body(menuResponseDtos)
                .build();
    }
}
