package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.InteresDtoPost;
import tfg.socialmediamusicapi.dto.InteresDtoPut;
import tfg.socialmediamusicapi.dto.mapper.InteresMapper;
import tfg.socialmediamusicapi.repository.InteresRepository;
import tfg.socialmediamusicapi.service.InteresService;

@Service
public class InteresServiceImpl implements InteresService {

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

    @Override
    public void createInteres(InteresDtoPost interesDto) {

	Interes interes = mapper.fromDtoPost(interesDto);

	repository.save(interes);

    }

    @Override
    public void modificarInteres(long id, InteresDtoPut interesDto) {
	Optional<Interes> interesEntity = repository.findById(id);

	if (interesEntity.isPresent()) {

	    Interes interes = interesEntity.orElseThrow();
	    interes.setNombre(interesDto.getNombre());

	    repository.save(interes);

	} else {
	    throw new IllegalArgumentException(message);
	}
    }

    @Override
    public void eliminarInteres(long id) {
	Optional<Interes> interesEntity = repository.findById(id);

	if (interesEntity.isPresent()) {
	    Interes interes = interesEntity.orElseThrow();
	    interes.getUsuarios().forEach(usuario -> usuario.getIntereses().remove(interes));
	    interes.getUsuarios().clear();
	    repository.delete(interes);
	} else {

	    throw new IllegalArgumentException(message);

	}
	
    }

}
