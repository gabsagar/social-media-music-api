package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Usuario;
import tfg.socialmediamusicapi.dto.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    
    UsuarioDto fromEnity(Usuario entity);
    
    List<UsuarioDto> fromDtoList(List<Usuario> entities);
    
    Usuario fromDto(UsuarioDto dto);
    
    List<Usuario> fromEntityList(List<UsuarioDto> dtos);


}
