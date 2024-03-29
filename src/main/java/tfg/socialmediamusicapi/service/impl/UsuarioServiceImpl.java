package tfg.socialmediamusicapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Usuario;
import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;
import tfg.socialmediamusicapi.dto.mapper.UsuarioMapper;
import tfg.socialmediamusicapi.repository.UsuarioRepository;
import tfg.socialmediamusicapi.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

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
   
    

}

    
