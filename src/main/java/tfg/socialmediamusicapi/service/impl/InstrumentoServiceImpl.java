package tfg.socialmediamusicapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.InstrumentoDto;
import tfg.socialmediamusicapi.dto.mapper.InstrumentoMapper;
import tfg.socialmediamusicapi.repository.InstrumentoRepository;
import tfg.socialmediamusicapi.service.InstrumentoService;

@Service
public class InstrumentoServiceImpl implements InstrumentoService{
    
    
    @Autowired
    private InstrumentoRepository repository;
    
    @Autowired
    private InstrumentoMapper mapper;

    @Override
    public List<InstrumentoDto> getAllInstrumentos() {
	
	List<Instrumento> instrumentos = repository.findAll();
	
	return mapper.fromDtoList(instrumentos);
	
	
    }

}
