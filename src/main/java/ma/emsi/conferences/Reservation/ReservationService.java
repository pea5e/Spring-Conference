package ma.emsi.conferences.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    IReservationRepository repo;
    public void add(Reservation s)
    {
        repo.save(s);
    }

    public Optional<Reservation> get(String code)
    {
        return repo.getByCode(code);
    }


}
