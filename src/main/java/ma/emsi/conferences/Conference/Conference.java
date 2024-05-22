package ma.emsi.conferences.Conference;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Conference")
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    @Transient
    private int place_reserver;
    private String Description;
    private String Title;
    private int Duree_minutes;
    private float prix;
    private String image;


}
