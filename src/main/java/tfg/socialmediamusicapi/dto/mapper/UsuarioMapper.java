package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Usuario;
import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    UsuarioDtoGet fromEnity(Usuario entity);
    
    List<UsuarioDtoGet> fromDtoList(List<Usuario> entities);
    
    Usuario fromDto(UsuarioDtoGet dto);
    
    Usuario fromDtoPost(UsuarioDtoPost dto);
    
    List<Usuario> fromEntityList(List<UsuarioDtoGet> dtos);


}
