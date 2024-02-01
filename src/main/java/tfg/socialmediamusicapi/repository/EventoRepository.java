package tfg.socialmediamusicapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tfg.socialmediamusicapi.domain.Evento;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    

}
