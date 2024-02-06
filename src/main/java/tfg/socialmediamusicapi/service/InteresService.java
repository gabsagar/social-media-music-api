package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.InteresDtoGet;

public interface InteresService {
    
    List<InteresDtoGet> getAllIntereses();

    InteresDtoGet findById(long id);

}
