package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;
import tfg.socialmediamusicapi.dto.EventoDtoPut;
import tfg.socialmediamusicapi.dto.mapper.EventoMapper;
import tfg.socialmediamusicapi.repository.EventoRepository;
import tfg.socialmediamusicapi.repository.InstrumentoRepository;
import tfg.socialmediamusicapi.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository repository;
    
    @Autowired
    private InstrumentoRepository repositoryInstrumento;

    @Autowired
    private EventoMapper mapper;

    String message = "El id no existe";

    @Override
    public List<EventoDtoGet> getAllEventos() {

	List<Evento> eventos = repository.findAll();

	return mapper.fromDtoList(eventos);

    }

    @Override
    public EventoDtoGet findById(long id) {
	Optional<Evento> entity = repository.findById(id);

	if (entity.isPresent()) {
	    Evento evento = entity.orElseThrow();
	    return mapper.fromEnity(evento);

	} else {

	    throw new IllegalArgumentException(message);
	}
    }

    @Override
    public void createEvento(EventoDtoPost eventoDto) {

	Evento evento = mapper.fromDtoPost(eventoDto);

	repository.save(evento);

    }

    @Override
    public List<EventoDtoGet> findByTipo(String tipo) {

	List<Evento> eventos = repository.findByTipo(tipo);

	return mapper.fromDtoList(eventos);

    }

    @Override
    public void modificarEvento(long id, EventoDtoPut eventoDto) {
	Optional<Evento> eventoEntity = repository.findById(id);

	if (eventoEntity.isPresent()) {

	    Evento evento = eventoEntity.orElseThrow();
	    evento.setTitulo(eventoDto.getTitulo());
	    evento.setFecha(eventoDto.getFecha());
	    evento.setDireccion(eventoDto.getDireccion());
	    repository.save(evento);

	} else {
	    throw new IllegalArgumentException(message);
	}

    }

    @Override
    public void eliminarEvento(long id) {
	Optional<Evento> eventoEntity = repository.findById(id);

	if (eventoEntity.isPresent()) {
	    Evento evento = eventoEntity.orElseThrow();
	    evento.getUsuarios().forEach(usuario -> usuario.getEventos().remove(evento));
	    evento.getUsuarios().clear();
	    
	    evento.getInstrumentos().forEach(instrumento -> instrumento.getEventos().remove(evento));
	    evento.getInstrumentos().clear();

	    repository.delete(evento);
	} else {

	    throw new IllegalArgumentException(message);

	}

    }

    @Override
    public void agregarInstrumentoEvento(long eventoId, long instrumentoId) {
	Optional<Evento> eventoEntity = repository.findById(eventoId);
	Optional<Instrumento> isntrumentoEntity = repositoryInstrumento.findById(instrumentoId);

	if (eventoEntity.isPresent() && isntrumentoEntity.isPresent()) {

	    Evento evento = eventoEntity.orElseThrow();
	    Instrumento isntrumento = isntrumentoEntity.orElseThrow();

	    evento.getInstrumentos().add(isntrumento);
	    isntrumento.getEventos().add(evento);

	    repository.save(evento);
	    repositoryInstrumento.save(isntrumento);

	} else {

	    throw new IllegalArgumentException(message);
	}
	
    }

}
