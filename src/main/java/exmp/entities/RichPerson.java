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

    public class Money {
        private int amount = 150;

        public Money() {}

        public Money(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}