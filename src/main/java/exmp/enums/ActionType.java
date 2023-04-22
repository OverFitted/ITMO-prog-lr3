package exmp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    MOVE("Перейти в другую локацию"),
    TALK("Поговорить с персонажем"),
    ATTACK("Атаковать персонажа"),
    QUIT("Завершить игру");

    private final String description;
}
