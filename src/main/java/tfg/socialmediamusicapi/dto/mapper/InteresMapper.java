package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.dto.InteresDto;

@Mapper(componentModel = "spring")
public interface InteresMapper {
    

    InteresDto fromEnity(Interes entity);
    
    List<InteresDto> fromDtoList(List<Interes> entities);
    
    Interes fromDto(InteresDto dto);
    
    List<Interes> fromEntityList(List<InteresDto> dtos);


}
