package ma.emsi.conferences.Reservation;


import ma.emsi.conferences.Conference.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(nativeQuery = true , value = "select * from reservation WHERE code = ?1 ")
    Optional<Reservation> getByCode(String code);
}
