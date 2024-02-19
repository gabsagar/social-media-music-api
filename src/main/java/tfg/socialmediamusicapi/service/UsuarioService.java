package tfg.socialmediamusicapi.service;



import java.util.List;

import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;
import tfg.socialmediamusicapi.dto.UsuarioDtoPut;


public interface UsuarioService {
    
    List<UsuarioDtoGet> getAllUsuarios();
    
    UsuarioDtoGet findById(long id);

    void createUsuario(UsuarioDtoPost usuarioDto);


    void modificarUsuario(long id, UsuarioDtoPut usuarioDto);

    void agregarInteres(long usuarioId, long interesId);

    void eliminarUsuario(long id);

    void eliminarInteres(long usuarioId, long interesId);

    void agregarEvento(long usuarioId, long eventoId);

    void eliminarEvento(long usuarioId, long eventoId);

    void agregarInstrumento(long usuarioId, long instrumentoId);

   
    
}
