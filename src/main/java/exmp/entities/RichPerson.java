package exmp.entities;

import exmp.locations.Location;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RichPerson extends BaseCharacter {
    private Council council;

    public RichPerson(String name, String description, Location location, Council council) {
        this.name = name;
        this.description = description;
        this.setLocation(location);
        this.council = council;
    }
}