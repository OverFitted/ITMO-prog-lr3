package exmp.entities;

import exmp.locations.Location;
import exmp.entities.Character;

import java.util.List;

public class SpecialCharacter extends Character {
    private final List<String> dialogLines;

    public SpecialCharacter(String name, String description, Location location, List<String> dialogLines) {
        super(name, description, location);
        this.dialogLines = dialogLines;
    }

    public void interact(Character player) {
        System.out.println(getName() + " говорит " + player.getName() + ":");
        for (String line : dialogLines) {
            System.out.println(line);
        }
    }
}
