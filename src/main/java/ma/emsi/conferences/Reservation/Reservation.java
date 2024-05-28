package ma.emsi.conferences.Reservation;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.conferences.Auth.User;
import ma.emsi.conferences.Conference.Conference;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int siege;
    private String code;
    @ManyToOne
    @JoinColumn(name="conference_id", nullable=false)
    private Conference conference;
    private String userEmail;
}
