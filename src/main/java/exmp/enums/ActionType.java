package exmp.enums;

public enum ActionType {
    MOVE("Перейти в другую локацию"),
    TALK("Поговорить с персонажем"),
    QUIT("Завершить игру");

    private final String description;

    ActionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
