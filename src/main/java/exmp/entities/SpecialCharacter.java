package exmp.entities;

import exmp.locations.Location;

import java.util.List;

public class SpecialCharacter extends BaseCharacter {
    private final List<String> dialogLines;
    private static int interacted = 0;

    public SpecialCharacter(String name, String description, Location location, List<String> dialogLines) {
        super(name, description, location);
        this.dialogLines = dialogLines;
    }

    public String interact() {
        if (dialogLines.size() < interacted)
            return dialogLines.get(++interacted);
        else if (dialogLines.size() == 1)
            return dialogLines.get(interacted);
        else
            return "К сожалению, мне больше нечего сказать";
    }
}
