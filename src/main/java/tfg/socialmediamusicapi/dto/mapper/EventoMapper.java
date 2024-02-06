package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;


@Mapper(componentModel = "spring")
public interface EventoMapper {
    
    EventoDtoGet fromEnity(Evento entity);
    
    List<EventoDtoGet> fromDtoList(List<Evento> entities);
    
    Evento fromDto(EventoDtoGet dto);
    
    Evento fromDtoPost(EventoDtoPost dto);
    
    List<Evento> fromEntityList(List<EventoDtoGet> dtos);

    
}
