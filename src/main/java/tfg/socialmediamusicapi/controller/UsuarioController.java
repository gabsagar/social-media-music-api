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
    public ResponseEntity<UsuarioDtoGet> findById(@PathVariable("id") long id) {
        try {
            UsuarioDtoGet usuario = service.findById(id);
            return ResponseEntity.ok(usuario);
            
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Usuario no encontrado con ID: " + id);
        }
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
        try {
            service.modificarUsuario(id, usuarioDto);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con ID " + id + " no existe");
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el usuario");
        }
    }


    @PutMapping("/{usuarioId}/intereses/{interesId}")
    @Operation(summary = "Añade un nuevo interés a un usuario")
    public ResponseEntity<String> agregarInteresUsuario(@PathVariable long usuarioId, @PathVariable long interesId) {
	try {
	    service.agregarInteres(usuarioId, interesId);
	    return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);

	} catch (NoSuchElementException ex) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());

	} catch (Exception ex) {
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    .body("Error al agregar el interés al usuario");
	}
    }

    @DeleteMapping("/{usuarioId}/intereses/{interesId}")
    @Operation(summary = "Elimina un interés de un usuario")
    public ResponseEntity<String> eliminarInteresUsuario(@PathVariable long usuarioId, @PathVariable long interesId) {
        try {
            service.eliminarInteres(usuarioId, interesId);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el interés del usuario");
        }
    }


    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Elimina un usuario y sus relaciones")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") long id) {
        try {
            service.eliminarUsuario(id);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
        } 
    }

    
    @PutMapping("/{usuarioId}/evento/{eventoId}")
    @Operation(summary = "Añade un nuevo evento a un usuario existente")
    public ResponseEntity<String> agregarEventoUsuario(@PathVariable long usuarioId, @PathVariable long eventoId) {
        try {
            service.agregarEvento(usuarioId, eventoId);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
        } catch (Exception ex) {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el evento al usuario");
        }
    }

    
    @DeleteMapping("/{usuarioId}/evento/{eventoId}")
    @Operation(summary = "Elimina un evento de un usuario")
    public ResponseEntity<String> eliminarEventoUsuario(@PathVariable long usuarioId, @PathVariable long eventoId) {
        try {
            service.eliminarEvento(usuarioId, eventoId);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el evento del usuario");
        }
    }

    
    @PutMapping("/{usuarioId}/instrumento/{instrumentoId}")
    @Operation(summary = "Añade un nuevo instrumento a un usuario existente")
    public ResponseEntity<String> agregarInstrumentoUsuario(@PathVariable long usuarioId, @PathVariable long instrumentoId) {
        try {
            service.agregarInstrumento(usuarioId, instrumentoId);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el instrumento al usuario");
        }
    }

    
    @DeleteMapping("/{usuarioId}/instrumento/{isntrumentoId}")
    @Operation(summary = "Elimina un instrumento de un usuario")
    public ResponseEntity<String> eliminarInstrumentoUsuario(@PathVariable long usuarioId, @PathVariable long isntrumentoId) {
        try {
            service.eliminarInstrumento(usuarioId, isntrumentoId);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el instrumneto del usuario");
        }
    }
    
    

}
