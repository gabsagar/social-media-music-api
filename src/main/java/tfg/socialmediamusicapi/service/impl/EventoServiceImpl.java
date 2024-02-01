package tfg.socialmediamusicapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.dto.EventoDto;
import tfg.socialmediamusicapi.dto.mapper.EventoMapper;
import tfg.socialmediamusicapi.repository.EventoRepository;
import tfg.socialmediamusicapi.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService{
    
    @Autowired
    private EventoRepository repository;
    
    @Autowired
    private EventoMapper mapper;

    @Override
    public List<EventoDto> getAllEventos() {
	
	List<Evento> eventos = repository.findAll();
	
	return mapper.fromDtoList(eventos);
	
    }

}
