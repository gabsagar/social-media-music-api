package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.InstrumentoDto;

@Mapper(componentModel = "spring")
public interface InstrumentoMapper {
    
    InstrumentoDto fromEnity(Instrumento entity);
    
    List<InstrumentoDto> fromDtoList(List<Instrumento> entities);
    
    Instrumento fromDto(InstrumentoDto dto);
    
    List<Instrumento> fromEntityList(List<InstrumentoDto> dtos);

}
