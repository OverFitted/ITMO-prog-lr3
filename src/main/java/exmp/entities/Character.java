package exmp.entities;

import exmp.locations.ILocatable;
import exmp.locations.Location;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Character extends BaseEntity implements ILocatable {
    private Location location;

    public Character(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
