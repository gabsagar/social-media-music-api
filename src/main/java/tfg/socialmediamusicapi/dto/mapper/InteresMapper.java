package tfg.socialmediamusicapi.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.dto.InteresDtoGet;

@Mapper(componentModel = "spring")
public interface InteresMapper {
    

    InteresDtoGet fromEnity(Interes entity);
    
    List<InteresDtoGet> fromDtoList(List<Interes> entities);
    
    Interes fromDto(InteresDtoGet dto);
    
    List<Interes> fromEntityList(List<InteresDtoGet> dtos);


}
