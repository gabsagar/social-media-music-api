package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPut;
import tfg.socialmediamusicapi.dto.mapper.InstrumentoMapper;
import tfg.socialmediamusicapi.repository.InstrumentoRepository;
import tfg.socialmediamusicapi.service.InstrumentoService;

@Service
public class InstrumentoServiceImpl implements InstrumentoService{
    
    
    @Autowired
    private InstrumentoRepository repository;
    
    @Autowired
    private InstrumentoMapper mapper;
    
    String message = "El id no existe";

    @Override
    public List<InstrumentoDtoGet> getAllInstrumentos() {
	
	List<Instrumento> instrumentos = repository.findAll();
	
	return mapper.fromDtoList(instrumentos);
	
	
    }

    @Override
    public InstrumentoDtoGet findById(long id) {
	Optional<Instrumento> entity = repository.findById(id);

	if (entity.isPresent()) {
	    Instrumento instrumento = entity.orElseThrow();
	    return mapper.fromEnity(instrumento);

	} else {

	    throw new IllegalArgumentException(message);
	}
    }

    @Override
    public void createInstrumento(InstrumentoDtoPost instrumentoDto) {
	Instrumento instrumento = mapper.fromDtoPost(instrumentoDto);

	repository.save(instrumento);

    }

    @Override
    public void modificarInstrumento(long id, InstrumentoDtoPut instrumentoDto) {
	Optional<Instrumento> instrumentoEntity = repository.findById(id);

	if (instrumentoEntity.isPresent()) {

	    Instrumento instrumento = instrumentoEntity.orElseThrow();
	    instrumento.setNombre(instrumentoDto.getNombre());

	    repository.save(instrumento);

	} else {
	    throw new IllegalArgumentException(message);
	}

    }

    @Override
    public void eliminarInstrumento(long id) {
	Optional<Instrumento> instrumentoEntity = repository.findById(id);

	if (instrumentoEntity.isPresent()) {
	    Instrumento instrumento = instrumentoEntity.orElseThrow();
	    instrumento.getUsuarios().forEach(usuario -> usuario.getInstrumentos().remove(instrumento));
	    instrumento.getUsuarios().clear();
	    
	    instrumento.getEventos().forEach(evento -> evento.getInstrumentos().remove(instrumento));
	    instrumento.getEventos().clear();
	    
	    repository.delete(instrumento);
	    
	} else {

	    throw new IllegalArgumentException(message);

	}
	
    }

}
