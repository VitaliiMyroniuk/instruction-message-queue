package ua.company.myroniuk.message;

public enum InstructionType {

    A(Priority.HIGH),

    B(Priority.MEDIUM),

    C(Priority.LOW),

    D(Priority.LOW);

    private final Priority priority;

    InstructionType(Priority priority) {
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

}
