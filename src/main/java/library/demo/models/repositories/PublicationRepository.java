package library.demo.models.repositories;

import library.demo.models.Publication;
import library.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Integer> {
    @Query("SELECT t FROM Publication t where t.user = ?1")
    List<Publication> findByUserName(User userName);
}
