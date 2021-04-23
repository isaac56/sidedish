package team16.sidedish.dto.response.detail;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DetailListResponseDto {
    private String hash;
    private List<DetailResponseDto> data;
}
