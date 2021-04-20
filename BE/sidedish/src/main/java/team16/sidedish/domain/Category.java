package team16.sidedish.domain;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private Integer id;

    private String name;

    private boolean isBest;

    public Category(String name, boolean isBest) {
        this.name = name;
        this.isBest = isBest;
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

    public boolean isBest() {
        return isBest;
    }

    public void setBest(boolean best) {
        isBest = best;
    }


}
