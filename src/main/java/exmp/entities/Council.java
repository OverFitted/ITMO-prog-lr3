package exmp.entities;

import exmp.locations.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Council {
    private String name;
    private String description;
    private List<RichPerson> members;
}