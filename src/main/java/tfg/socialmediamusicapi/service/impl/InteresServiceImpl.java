package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
	Interes interes = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El interes con ID " + id + " no existe"));

	return mapper.fromEnity(interes);
    }

    @Override
    public void createInteres(InteresDtoPost interesDto) {

	Interes interes = mapper.fromDtoPost(interesDto);

	repository.save(interes);

    }

    @Override
    public void modificarInteres(long id, InteresDtoPut interesDto) {
	Interes interes = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El interes con ID " + id + " no existe"));

	interes.setNombre(interesDto.getNombre());

	repository.save(interes);
    }

    @Override
    public void eliminarInteres(long id) {
	Interes interes = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El interes con ID " + id + " no existe"));

	interes.getUsuarios().forEach(usuario -> usuario.getIntereses().remove(interes));
	interes.getUsuarios().clear();
	repository.delete(interes);

    }

}
