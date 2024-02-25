package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
public class InstrumentoServiceImpl implements InstrumentoService {

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
	Instrumento instrumento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + id + " no existe"));

	return mapper.fromEnity(instrumento);
    }

    @Override
    public void createInstrumento(InstrumentoDtoPost instrumentoDto) {
	Instrumento instrumento = mapper.fromDtoPost(instrumentoDto);

	repository.save(instrumento);

    }

    @Override
    public void modificarInstrumento(long id, InstrumentoDtoPut instrumentoDto) {
	Instrumento instrumento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + id + " no existe"));

	instrumento.setNombre(instrumentoDto.getNombre());

	repository.save(instrumento);

    }

    @Override
    public void eliminarInstrumento(long id) {
	Instrumento instrumento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + id + " no existe"));

	instrumento.getUsuarios().forEach(usuario -> usuario.getInstrumentos().remove(instrumento));
	instrumento.getUsuarios().clear();

	instrumento.getEventos().forEach(evento -> evento.getInstrumentos().remove(instrumento));
	instrumento.getEventos().clear();

	repository.delete(instrumento);

    }

}
