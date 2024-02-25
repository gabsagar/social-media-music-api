package tfg.socialmediamusicapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<EventoDtoGet> finById(@PathVariable("id") long id) {
	try {
	    EventoDtoGet evento = service.findById(id);
	    return ResponseEntity.ok(evento);

	} catch (NoSuchElementException ex) {
	    throw new NoSuchElementException("Evento no encontrado con ID: " + id);
	}

    }

    @GetMapping("/eventosTipo/{tipo}")
    @Operation(summary = "Recupera eventos por tipo")
    public ResponseEntity<List<EventoDtoGet>> findByTipo(@PathVariable("tipo") String tipo) {
	try {
	    List<EventoDtoGet> eventos = service.findByTipo(tipo);
	    return new ResponseEntity<>(eventos, HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
    }

    @GetMapping("/eventosFecha/{fecha}")
    @Operation(summary = "Recupera eventos por fecha")
    public ResponseEntity<List<EventoDtoGet>> findByFecha(
	    @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
	try {
	    List<EventoDtoGet> eventos = service.findByFecha(fecha);
	    return new ResponseEntity<>(eventos, HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
    }

    @GetMapping("/eventosSemana")
    @Operation(summary = "Recupera eventos para la semana actual")
    public ResponseEntity<List<EventoDtoGet>> findEventosEstaSemana() {
	try {
	    List<EventoDtoGet> eventos = service.findEventosEstaSemana();
	    return new ResponseEntity<>(eventos, HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
    }

    @GetMapping("/eventosFinDeSemana")
    @Operation(summary = "Recupera eventos para el próximo fin de semana")
    public ResponseEntity<List<EventoDtoGet>> findEventosProximoFinDeSemana() {
        try {
            List<EventoDtoGet> eventos = service.findEventosFinDeSemana();
            if (eventos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
	try {
	    service.modificarEvento(id, eventoDto);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El evento con ID " + id + " no existe");
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el evento");
	}
    }

    @DeleteMapping("/eventos/{id}")
    @Operation(summary = "Elimina un evento y sus relaciones")
    public ResponseEntity<String> eliminarEvento(@PathVariable("id") long id) {
	try {
	    service.eliminarEvento(id);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
	} catch (EntityNotFoundException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento no encontrado: " + ex.getMessage());
	}
    }

    @PutMapping("/{eventoId}/instrumentoEvento/{instrumentoId}")
    @Operation(summary = "Añade un nuevo instrumento a un evento")
    public ResponseEntity<String> agregarInstrumentoEvento(@PathVariable long eventoId,
	    @PathVariable long instrumentoId) {
	try {
	    service.agregarInstrumentoEvento(eventoId, instrumentoId);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    .body("Error al agregar el instrumento al evento");
	}
    }

}
