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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import lombok.Data;
import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;
import tfg.socialmediamusicapi.dto.UsuarioDtoPut;
import tfg.socialmediamusicapi.service.UsuarioService;

@RestController
@Api(tags = "Usuario Api")
@Data
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    @Operation(summary = "Recupera todos los usuarios")
    public List<UsuarioDtoGet> getAllUsuarios() {

	return service.getAllUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Recupera un usuario por id")
    public UsuarioDtoGet finById(@PathVariable("id") long id) {

	return service.findById(id);
    }

    @PostMapping("/usuarios")
    @Operation(summary = "Guarda un usuario")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioDtoPost usuarioDto) {
	service.createUsuario(usuarioDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    @PutMapping("/usuarios/{id}")
    @Operation(summary = "Modifica un usuario")
    public ResponseEntity<String> modificarUsuario(@PathVariable("id") long id, @RequestBody UsuarioDtoPut usuarioDto) {
	service.modificarUsuario(id, usuarioDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    @PutMapping("/{usuarioId}/intereses/{interesId}")
    @Operation(summary = "Añade un nuevo interés a un usuario")
    public ResponseEntity<String> agregarInteresUsuario(@PathVariable long usuarioId, @PathVariable long interesId) {
	service.agregarInteres(usuarioId, interesId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/{usuarioId}/intereses/{interesId}")
    @Operation(summary = "Elimina un interés de un usuario")
    public ResponseEntity<String> eliminarInteresUsuario(@PathVariable long usuarioId, @PathVariable long interesId) {
	service.eliminarInteres(usuarioId, interesId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Elimina un usuario y sus relaciones")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") long id) {
	service.eliminarUsuario(id);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    @PutMapping("/{usuarioId}/evento/{eventoId}")
    @Operation(summary = "Añade un nuevo evento a un usuario existente")
    public ResponseEntity<String> agregarEventoUsuario(@PathVariable long usuarioId, @PathVariable long eventoId) {
	service.agregarEvento(usuarioId, eventoId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    @DeleteMapping("/{usuarioId}/evento/{eventoId}")
    @Operation(summary = "Elimina un evento de un usuario")
    public ResponseEntity<String> eliminarEventoUsuario(@PathVariable long usuarioId, @PathVariable long eventoId) {
	service.eliminarEvento(usuarioId, eventoId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    
    @PutMapping("/{usuarioId}/instrumento/{instrumentoId}")
    @Operation(summary = "Añade un nuevo instrumento a un usuario existente")
    public ResponseEntity<String> agregarInstrumentoUsuario(@PathVariable long usuarioId, @PathVariable long instrumentoId) {
	service.agregarInstrumento(usuarioId, instrumentoId);
	
	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
    

    
    

}
