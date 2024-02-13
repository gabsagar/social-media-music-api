package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.InteresDtoPost;

public interface InteresService {
    
    List<InteresDtoGet> getAllIntereses();

    InteresDtoGet findById(long id);

    void createInteres(InteresDtoPost interesDto);

}