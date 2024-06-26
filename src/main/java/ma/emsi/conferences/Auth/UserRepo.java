package ma.emsi.conferences.Auth;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    @Query(nativeQuery = true , value = "select * from users_conf where email=?1 ")
    Optional<User> getUserByEmail(String Email);

    @Modifying
    @Query(nativeQuery = true , value = "update users_conf set enabled = true where id=?1 ")
    void verify(int id);

}
