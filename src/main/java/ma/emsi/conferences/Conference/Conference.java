package ma.emsi.conferences.Conference;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.conferences.Auth.User;
import ma.emsi.conferences.Reservation.Reservation;
import ma.emsi.conferences.Salle.Salle;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private String description;
    private String Title;
    private int duree_minutes;
    @ManyToOne
    @JoinColumn(name = "fk_id_salle")
    private Salle salle;
    @ManyToOne
    @JoinColumn(name = "fk_id_user")
    private User org;
    @OneToMany(mappedBy="conference")
    private List<Reservation> reservations;

}
