package exmp.entities;

import exmp.locations.ILocatable;
import exmp.locations.Location;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseCharacter extends BaseEntity implements ILocatable {
    private Location location;

    public BaseCharacter(String name, String description, Location location) {
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

    public static class Inventory {
        private List<Item> items = new ArrayList<>();

        public Inventory() {}

        public Inventory(List<Item> items) {
            this.items = items;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
