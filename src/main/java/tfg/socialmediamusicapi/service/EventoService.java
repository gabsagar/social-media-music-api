package tfg.socialmediamusicapi.service;

import java.time.LocalDateTime;
import java.util.List;

import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;
import tfg.socialmediamusicapi.dto.EventoDtoPut;

public interface EventoService {

    List<EventoDtoGet> getAllEventos();

    List<EventoDtoGet> findByTipo(String tipo);

    EventoDtoGet findById(long id);

    void createEvento(EventoDtoPost eventoDto);

    void modificarEvento(long id, EventoDtoPut eventoDto);

    void eliminarEvento(long id);

    void agregarInstrumentoEvento(long eventoId, long instrumentoId);

    List<EventoDtoGet> findByFecha(LocalDateTime fecha);

    List<EventoDtoGet> findEventosEstaSemana();

    List<EventoDtoGet> findEventosFinDeSemana();

}
