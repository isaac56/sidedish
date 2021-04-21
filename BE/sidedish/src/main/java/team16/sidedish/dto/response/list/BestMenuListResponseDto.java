package team16.sidedish.dto.response.list;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team16.sidedish.dto.response.BestMenuResponseDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BestMenuListResponseDto {
    private List<BestMenuResponseDto> body;
}
