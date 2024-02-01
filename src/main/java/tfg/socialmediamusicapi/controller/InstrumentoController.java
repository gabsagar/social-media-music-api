package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tfg.socialmediamusicapi.dto.InstrumentoDto;
import tfg.socialmediamusicapi.service.InstrumentoService;

@RestController
public class InstrumentoController {
    
    @Autowired
    private InstrumentoService service;
    
    @GetMapping("/instrumentos")
    public List<InstrumentoDto> getAllUsuarios(){
	
	return service.getAllInstrumentos();
    }

}
