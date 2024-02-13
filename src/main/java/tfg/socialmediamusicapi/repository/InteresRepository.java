package tfg.socialmediamusicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.socialmediamusicapi.domain.Interes;

@Repository
public interface InteresRepository extends JpaRepository<Interes, Long> {

}