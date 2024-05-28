package ma.emsi.conferences.Reservation;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ma.emsi.conferences.Conference.Conference;

import java.time.LocalDate;

public class ReservationDTO {
    public int siege;
    public String title;
    public LocalDate date;
    public String salle;
    public int duree;
    public String email;

    public ReservationDTO() {
    }

    public ReservationDTO(int siege, String title, LocalDate date, String salle, int duree, String email) {
        this.siege = siege;
        this.title = title;
        this.date = date;
        this.salle = salle;
        this.duree = duree;
        this.email = email;
    }
}
