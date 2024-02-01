package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.dto.EventoDto;


@Mapper(componentModel = "spring")
public interface EventoMapper {
    
    EventoDto fromEnity(Evento entity);
    
    List<EventoDto> fromDtoList(List<Evento> entities);
    
    Evento fromDto(EventoDto dto);
    
    List<Evento> fromEntityList(List<EventoDto> dtos);

    
}
