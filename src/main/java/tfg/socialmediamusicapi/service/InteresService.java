package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.InteresDtoPost;
import tfg.socialmediamusicapi.dto.InteresDtoPut;

public interface InteresService {
    
    List<InteresDtoGet> getAllIntereses();

    InteresDtoGet findById(long id);

    void createInteres(InteresDtoPost interesDto);

    void modificarInteres(long id, InteresDtoPut interesDto);

    void eliminarInteres(long id);

}