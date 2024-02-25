package tfg.socialmediamusicapi.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.InteresDtoPost;
import tfg.socialmediamusicapi.dto.InteresDtoPut;
import tfg.socialmediamusicapi.service.InteresService;

@RestController
@Api(tags = "Interes Api")
public class InteresController {

    @Autowired
    private InteresService service;

    @GetMapping("/intereses")
    @Operation(summary = "Recupera todos los intereses")
    public List<InteresDtoGet> getAllUsuarios() {

	return service.getAllIntereses();
    }

    @GetMapping("/intereses/{id}")
    @Operation(summary = "Recupera un interes por id")
    public ResponseEntity<InteresDtoGet> finById(@PathVariable("id") long id) {
	try {
	    InteresDtoGet interes = service.findById(id);
	    return ResponseEntity.ok(interes);

	} catch (NoSuchElementException ex) {
	    throw new NoSuchElementException("Interes no encontrado con ID: " + id);
	}

    }

    @PostMapping("/intereses")
    @Operation(summary = "Guarda un interes")
    public ResponseEntity<String> crearInteres(@RequestBody InteresDtoPost interesDto) {
	service.createInteres(interesDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    @PutMapping("/intereses/{id}")
    @Operation(summary = "Modifica un interes")
    public ResponseEntity<String> modificarInteres(@PathVariable("id") long id, @RequestBody InteresDtoPut interesDto) {
	try {
	    service.modificarInteres(id, interesDto);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El interes con ID " + id + " no existe");
	} catch (Exception ex) {

	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el usuario");
	}
    }

    @DeleteMapping("/intereses/{id}")
    @Operation(summary = "Elimina un interes y sus relaciones")
    public ResponseEntity<String> eliminarInteres(@PathVariable("id") long id) {
	try {
	    service.eliminarInteres(id);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
	} catch (EntityNotFoundException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Interes no encontrado: " + ex.getMessage());
	}
    }

}