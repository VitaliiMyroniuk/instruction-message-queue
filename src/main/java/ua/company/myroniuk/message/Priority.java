package ua.company.myroniuk.message;

public enum Priority {

    HIGH(1),

    MEDIUM(2),

    LOW(3);

    private final Integer value;

    Priority(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
