package ma.emsi.conferences.Salle;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISalleRepository extends JpaRepository<Salle, Integer> {

    @Modifying
    @Query(nativeQuery = true , value = "UPDATE Salle set nombre_places = ?2 , name = ?3 WHERE id = ?1 ")
    void Update(int id, int places, String name);

}
