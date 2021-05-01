package team16.sidedish.domain.enums;

public enum CategoryEnum {
    MAIN(1),
    SOUP(2),
    SIDE(3);

    private int id;

    private CategoryEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
