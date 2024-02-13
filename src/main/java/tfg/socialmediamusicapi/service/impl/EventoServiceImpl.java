package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;
import tfg.socialmediamusicapi.dto.mapper.EventoMapper;
import tfg.socialmediamusicapi.repository.EventoRepository;
import tfg.socialmediamusicapi.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {
    
    @Autowired
    private EventoRepository repository;

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


}
