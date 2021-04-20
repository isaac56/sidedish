package team16.sidedish.domain;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Badge {
    @Id
    private Long id;

    private String name;

    public Badge(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
