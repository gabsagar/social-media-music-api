package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
	Usuario usuario = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El usuario con ID " + id + " no existe"));

	return mapper.fromEnity(usuario);
    }

    @Override
    public void createUsuario(UsuarioDtoPost usuarioDto) {

	Usuario usuario = mapper.fromDtoPost(usuarioDto);

	repository.save(usuario);

    }

    @Override
    public void modificarUsuario(long id, UsuarioDtoPut usuarioDto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + id + " no existe"));

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setCiudad(usuarioDto.getCiudad());
        usuario.setAgrupacion(usuarioDto.getAgrupacion());
        repository.save(usuario);
    }


    @Override
    public void agregarInteres(long usuarioId, long interesId) {
	Usuario usuario = repository.findById(usuarioId)
		.orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

	Interes interes = repositoryInteres.findById(interesId)
		.orElseThrow(() -> new NoSuchElementException("El interés con ID " + interesId + " no existe"));

	usuario.getIntereses().add(interes);
	interes.getUsuarios().add(usuario);

	repository.save(usuario);
	repositoryInteres.save(interes);

    }

    @Override
    public void eliminarUsuario(long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + id + " no existe"));

        usuario.getIntereses().forEach(interes -> interes.getUsuarios().remove(usuario));
        usuario.getIntereses().clear();
        usuario.getEventos().forEach(evento -> evento.getUsuarios().remove(usuario));
        usuario.getEventos().clear();
        usuario.getInstrumentos().forEach(isntrumento -> isntrumento.getUsuarios().remove(usuario));
        usuario.getInstrumentos().clear();
        
        repository.delete(usuario);
    }


    @Override
    public void eliminarInteres(long usuarioId, long interesId) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

        Interes interes = repositoryInteres.findById(interesId)
                .orElseThrow(() -> new NoSuchElementException("El interés con ID " + interesId + " no existe"));

        usuario.getIntereses().remove(interes);
        interes.getUsuarios().remove(usuario);

        repository.save(usuario);
        repositoryInteres.save(interes);
    }


    @Override
    public void agregarEvento(long usuarioId, long eventoId) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

        Evento evento = repositoryEvento.findById(eventoId)
                .orElseThrow(() -> new NoSuchElementException("El evento con ID " + eventoId + " no existe"));

        usuario.getEventos().add(evento);
        evento.getUsuarios().add(usuario);

        repository.save(usuario);
        repositoryEvento.save(evento);
    }


    @Override
    public void eliminarEvento(long usuarioId, long eventoId) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

        Evento evento = repositoryEvento.findById(eventoId)
                .orElseThrow(() -> new NoSuchElementException("El evento con ID " + eventoId + " no existe"));

        usuario.getEventos().remove(evento);
        evento.getUsuarios().remove(usuario);

        repository.save(usuario);
        repositoryEvento.save(evento);
    }


    @Override
    public void agregarInstrumento(long usuarioId, long instrumentoId) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

        Instrumento instrumento = repositoryInstrumento.findById(instrumentoId)
                .orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + instrumentoId + " no existe"));

        usuario.getInstrumentos().add(instrumento);
        instrumento.getUsuarios().add(usuario);

        repository.save(usuario);
        repositoryInstrumento.save(instrumento);
    }

    @Override
    public void eliminarInstrumento(long usuarioId, long isntrumentoId) {
	Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("El usuario con ID " + usuarioId + " no existe"));

        Instrumento instrumento = repositoryInstrumento.findById(isntrumentoId)
                .orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + isntrumentoId + " no existe"));

        usuario.getInstrumentos().remove(instrumento);
        instrumento.getUsuarios().remove(usuario);

        repository.save(usuario);
        repositoryInstrumento.save(instrumento);
	
    }


    

}

    
