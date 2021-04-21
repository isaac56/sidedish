package team16.sidedish.domain.entity.lookUp;

import org.springframework.data.annotation.Id;

public class Badge {
    @Id
    private Integer id;

    private String name;

    public Badge(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
