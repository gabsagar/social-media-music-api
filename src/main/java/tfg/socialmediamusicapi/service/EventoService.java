package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;

public interface EventoService {

    List<EventoDtoGet> getAllEventos();

    List<EventoDtoGet> findByTipo(String tipo);

    EventoDtoGet findById(long id);

    void createEvento(EventoDtoPost eventoDto);

}
