package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.domain.Usuario;
import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;
import tfg.socialmediamusicapi.dto.UsuarioDtoPut;
import tfg.socialmediamusicapi.dto.mapper.UsuarioMapper;
import tfg.socialmediamusicapi.repository.EventoRepository;
import tfg.socialmediamusicapi.repository.InstrumentoRepository;
import tfg.socialmediamusicapi.repository.InteresRepository;
import tfg.socialmediamusicapi.repository.UsuarioRepository;
import tfg.socialmediamusicapi.service.UsuarioService;

@Service
@Data
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private InteresRepository repositoryInteres;
    
    @Autowired
    private EventoRepository repositoryEvento;
    
    @Autowired
    private InstrumentoRepository repositoryInstrumento;

    @Autowired
    private UsuarioMapper mapper;

    String message = "El id no existe";

    @Override
    public List<UsuarioDtoGet> getAllUsuarios() {
	List<Usuario> usuarios = repository.findAll();

	return mapper.fromDtoList(usuarios);
    }

    @Override
    public UsuarioDtoGet findById(long id) {

	Optional<Usuario> entity = repository.findById(id);

	if (entity.isPresent()) {
	    Usuario usuario = entity.orElseThrow();
	    return mapper.fromEnity(usuario);

	} else {

	    throw new IllegalArgumentException(message);
	}

    }

    @Override
    public void createUsuario(UsuarioDtoPost usuarioDto) {

	Usuario usuario = mapper.fromDtoPost(usuarioDto);

	repository.save(usuario);

    }

    @Override
    public void modificarUsuario(long id, UsuarioDtoPut usuarioDto) {
	Optional<Usuario> usuarioEntity = repository.findById(id);

	if (usuarioEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    usuario.setNombre(usuarioDto.getNombre());
	    usuario.setCiudad(usuarioDto.getCiudad());
	    usuario.setAgrupacion(usuarioDto.getAgrupacion());
	    repository.save(usuario);

	} else {
	    throw new IllegalArgumentException(message);
	}
    }

    @Override
    public void agregarInteres(long usuarioId, long interesId) {
	Optional<Usuario> usuarioEntity = repository.findById(usuarioId);
	Optional<Interes> interesEntity = repositoryInteres.findById(interesId);

	if (usuarioEntity.isPresent() && interesEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    Interes interes = interesEntity.orElseThrow();

	    usuario.getIntereses().add(interes);
	    interes.getUsuarios().add(usuario);

	    repository.save(usuario);
	    repositoryInteres.save(interes);

	} else {

	    throw new IllegalArgumentException(message);
	}

    }

    @Override
    public void eliminarUsuario(long id) {
	Optional<Usuario> usuarioEntity = repository.findById(id);

	if (usuarioEntity.isPresent()) {
	    Usuario usuario = usuarioEntity.orElseThrow();
	    usuario.getIntereses().forEach(interes -> interes.getUsuarios().remove(usuario));
	    usuario.getIntereses().clear();
	    usuario.getEventos().forEach(evento -> evento.getUsuarios().remove(usuario));
	    usuario.getEventos().clear();
	    repository.delete(usuario);
	} else {

	    throw new IllegalArgumentException(message);

	}
    }

    @Override
    public void eliminarInteres(long usuarioId, long interesId) {
	Optional<Usuario> usuarioEntity = repository.findById(usuarioId);
	Optional<Interes> interesEntity = repositoryInteres.findById(interesId);
	
	if (usuarioEntity.isPresent() && interesEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    Interes interes = interesEntity.orElseThrow();

	    usuario.getIntereses().remove(interes);
	    interes.getUsuarios().remove(usuario);

	    repository.save(usuario);
	    repositoryInteres.save(interes);

	} else {

	    throw new IllegalArgumentException(message);
	}
	
    }

    @Override
    public void agregarEvento(long usuarioId, long eventoId) {
	Optional<Usuario> usuarioEntity = repository.findById(usuarioId);
	Optional<Evento> eventoEntity =  repositoryEvento.findById(eventoId);

	if (usuarioEntity.isPresent() && eventoEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    Evento evento = eventoEntity.orElseThrow();

	    usuario.getEventos().add(evento);
	    evento.getUsuarios().add(usuario);

	    repository.save(usuario);
	    repositoryEvento.save(evento);

	} else {

	    throw new IllegalArgumentException(message);
	}
	
	
    }

    @Override
    public void eliminarEvento(long usuarioId, long eventoId) {
	Optional<Usuario> usuarioEntity = repository.findById(usuarioId);
	Optional<Evento> eventoEntity = repositoryEvento.findById(eventoId);
	
	if (usuarioEntity.isPresent() && eventoEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    Evento evento = eventoEntity.orElseThrow();

	    usuario.getEventos().remove(evento);
	    evento.getUsuarios().remove(usuario);

	    repository.save(usuario);
	    repositoryEvento.save(evento);

	} else {

	    throw new IllegalArgumentException(message);
	}
	
    }

    @Override
    public void agregarInstrumento(long usuarioId, long instrumentoId) {
	Optional<Usuario> usuarioEntity = repository.findById(usuarioId);
	Optional<Instrumento> isntrumentoEntity = repositoryInstrumento.findById(instrumentoId);

	if (usuarioEntity.isPresent() && isntrumentoEntity.isPresent()) {

	    Usuario usuario = usuarioEntity.orElseThrow();
	    Instrumento isntrumento = isntrumentoEntity.orElseThrow();

	    usuario.getInstrumentos().add(isntrumento);
	    isntrumento.getUsuarios().add(usuario);

	    repository.save(usuario);
	    repositoryInstrumento.save(isntrumento);

	} else {

	    throw new IllegalArgumentException(message);
	}
	
    }

    

}

    
