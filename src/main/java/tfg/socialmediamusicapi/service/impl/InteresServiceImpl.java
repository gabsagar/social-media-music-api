package tfg.socialmediamusicapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.dto.InteresDto;
import tfg.socialmediamusicapi.dto.mapper.InteresMapper;
import tfg.socialmediamusicapi.repository.InteresRepository;
import tfg.socialmediamusicapi.service.InteresService;

@Service
public class InteresServiceImpl implements InteresService{
    
    @Autowired
    private InteresRepository repository;
    
    @Autowired
    private InteresMapper mapper;

    @Override
    public List<InteresDto> getAllIntereses() {
	
	List<Interes> intereses = repository.findAll();
	
	return mapper.fromDtoList(intereses);
	
    }

}
