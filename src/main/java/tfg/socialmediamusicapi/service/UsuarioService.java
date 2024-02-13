package tfg.socialmediamusicapi.service;



import java.util.List;

import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;


public interface UsuarioService {
    
    List<UsuarioDtoGet> getAllUsuarios();
    
    UsuarioDtoGet findById(long id);

    void createUsuario(UsuarioDtoPost usuarioDto);

    void asignarInteres(Long usuarioId, Long interesId);
    
    
}
