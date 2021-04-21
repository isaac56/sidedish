package team16.sidedish.dto.response.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team16.sidedish.dto.response.MenuResponseDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SideMenuListResponseDto {
    private List<MenuResponseDto> sideList;
}
