package ma.emsi.conferences.Salle;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Salle")
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nombre_places;
    private String name;

}
