package tfg.socialmediamusicapi.service;



import java.util.List;

import tfg.socialmediamusicapi.dto.UsuarioDto;


public interface UsuarioService {
    
    List<UsuarioDto> getAllUsuarios();
    
    UsuarioDto findById(long id);
    
    
}
