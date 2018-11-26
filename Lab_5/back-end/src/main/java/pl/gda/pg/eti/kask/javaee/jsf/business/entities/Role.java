package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

public enum Role {
    ADMIN("Admin", 1),
    USER("User", 2);

    private final String key;
    private final Integer value;

    Role(String key, Integer value) {
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
