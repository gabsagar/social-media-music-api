package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;
import tfg.socialmediamusicapi.dto.EventoDtoPut;
import tfg.socialmediamusicapi.service.EventoService;

@RestController
@Api(tags = "Evento Api")
public class EventoController {

    @Autowired
    private EventoService service;

    @GetMapping("/eventos")
    @Operation(summary = "Recupera todos los eventos")
    public List<EventoDtoGet> getAllEventos() {

	return service.getAllEventos();
    }

    @GetMapping("/eventos/{id}")
    @Operation(summary = "Recupera un evento por id")
    public EventoDtoGet finById(@PathVariable("id") long id) {

	return service.findById(id);
    }

    @GetMapping("/eventosTipo/{tipo}")
    @Operation(summary = "Recupera eventos por tipo")
    public List<EventoDtoGet> finByTipo(@PathVariable("tipo") String tipo) {
	return service.findByTipo(tipo);

    }

    @PostMapping("/eventos")
    @Operation(summary = "Guarda un evento")
    public ResponseEntity<String> crearEvento(@RequestBody EventoDtoPost eventoDto) {
	service.createEvento(eventoDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    
    @PutMapping("/eventos/{id}")
    @Operation(summary = "Modifica un evento")
    public ResponseEntity<String> modificarEvento(@PathVariable("id") long id, @RequestBody EventoDtoPut eventoDto) {
	service.modificarEvento(id, eventoDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    @DeleteMapping("/eventos/{id}")
    @Operation(summary = "Elimina un evento y sus relaciones")
    public ResponseEntity<String> eliminarEvento(@PathVariable("id") long id) {
	service.eliminarEvento(id);
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    @PutMapping("/{eventoId}/instrumentoEvento/{instrumentoId}")
    @Operation(summary = "Añade un nuevo instrumento a un evento")
    public ResponseEntity<String> agregarInstrumentoEvento(@PathVariable long eventoId, @PathVariable long instrumentoId) {
	service.agregarInstrumentoEvento(eventoId, instrumentoId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

}
