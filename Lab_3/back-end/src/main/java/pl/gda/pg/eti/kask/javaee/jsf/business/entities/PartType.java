package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

public enum PartType {
    MOTHERBOARD("Płyta główna", 1),
    GRAPHIC_CARD("Karta graficzna", 2),
    HARD_DRIVE("Dysk twardy", 3),
    RAM("Pamięć RAM", 4),
    CASE("Obudowa", 5),
    COOLING("Chłodzenie", 6),
    POWER_SUPPLY("Zasilacz", 7),
    PROCESSOR("Procesor", 8);

    private final String key;
    private final Integer value;

    PartType(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}