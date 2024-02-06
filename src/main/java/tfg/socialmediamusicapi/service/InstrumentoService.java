package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;

public interface InstrumentoService {
    
    List<InstrumentoDtoGet> getAllInstrumentos();

    InstrumentoDtoGet findById(long id);

    void createInstrumento(InstrumentoDtoPost instrumentoDto);

   

}
