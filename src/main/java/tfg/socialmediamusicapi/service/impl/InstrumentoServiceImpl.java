package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;
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

}
