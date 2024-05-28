package ma.emsi.conferences.Auth;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerifyREpo extends JpaRepository<Verification,Integer> {

    @Query(nativeQuery = true , value = "select * from verify where fk_id_user=?1 ")
    Optional<Verification> getByUser(int id);
}
