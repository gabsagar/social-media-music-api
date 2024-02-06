package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.mapper.InteresMapper;
import tfg.socialmediamusicapi.repository.InteresRepository;
import tfg.socialmediamusicapi.service.InteresService;

@Service
public class InteresServiceImpl implements InteresService{
    
    @Autowired
    private InteresRepository repository;
    
    @Autowired
    private InteresMapper mapper;
    
    String message = "El id no existe";

    @Override
    public List<InteresDtoGet> getAllIntereses() {
	
	List<Interes> intereses = repository.findAll();
	
	return mapper.fromDtoList(intereses);
	
    }

    @Override
    public InteresDtoGet findById(long id) {
	Optional<Interes> entity = repository.findById(id);

	if (entity.isPresent()) {
	    Interes interes = entity.orElseThrow();
	    return mapper.fromEnity(interes);

	} else {

	    throw new IllegalArgumentException(message);
	}
    }

}
