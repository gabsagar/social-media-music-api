package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;

@Mapper(componentModel = "spring")
public interface InstrumentoMapper {
    
    InstrumentoDtoGet fromEnity(Instrumento entity);
    
    List<InstrumentoDtoGet> fromDtoList(List<Instrumento> entities);
    
    Instrumento fromDto(InstrumentoDtoGet dto);
    
    Instrumento fromDtoPost(InstrumentoDtoPost dto);
    
    List<Instrumento> fromEntityList(List<InstrumentoDtoGet> dtos);

}
