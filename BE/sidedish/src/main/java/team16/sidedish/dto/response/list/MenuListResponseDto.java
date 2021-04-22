package team16.sidedish.dto.response.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team16.sidedish.dto.response.MenuResponseDto;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuListResponseDto {
    private Set<MenuResponseDto> body;
}
