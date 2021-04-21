package team16.dummy.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailDataWrapper {
    String hash;
    DetailData data;
}
