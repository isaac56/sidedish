package team16.dummy.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DetailData {
    private List<String> thumb_images;
    private String product_description;
    private String point;
    private List<String> detail_section;

    public int getPoint() {
        if (point != null) {
            return Integer.valueOf(point.replaceAll("[^0-9]", ""));
        }
        return 0;
    }
}
