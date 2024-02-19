package tfg.socialmediamusicapi.service;

import java.util.List;

import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPut;

public interface InstrumentoService {
    
    List<InstrumentoDtoGet> getAllInstrumentos();

    InstrumentoDtoGet findById(long id);

    void createInstrumento(InstrumentoDtoPost instrumentoDto);

    void modificarInstrumento(long id, InstrumentoDtoPut instrumentoDto);

    void eliminarInstrumento(long id);

   

}
