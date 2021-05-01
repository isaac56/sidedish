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
public class SimpleData {
    private String detail_hash;
    private String image;
    private List<String> delivery_type;
    private String title;
    private String description;
    private String n_price;
    private String s_price;
    private List<String> badge;

    public String getProvider() {
        String provider = title.split("\\]")[0];
        provider = provider.replaceAll("[\\[\\] ]", "");
        return provider.trim();
    }

    public String getName() {
        String name = title.split("\\]")[1];
        return name.trim();
    }

    public int getOriginalPrice() {
        if (n_price != null) {
            return Integer.valueOf(n_price.replaceAll("[^0-9]", ""));
        }
        if (s_price != null) {
            return Integer.valueOf(s_price.replaceAll("[^0-9]", ""));
        }
        return 0;
    }

    public Integer getDiscountPrice() {
        if (s_price != null) {
            return Integer.valueOf(s_price.replaceAll("[^0-9]", ""));
        }
        return null;
    }
}
