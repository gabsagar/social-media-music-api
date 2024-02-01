package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tfg.socialmediamusicapi.dto.EventoDto;
import tfg.socialmediamusicapi.service.EventoService;


@RestController
public class EventoController {
    
    @Autowired
    private EventoService service;
    
    @GetMapping("/eventos")
    public List<EventoDto> getAllUsuarios(){
	
	return service.getAllEventos();
    }

}
