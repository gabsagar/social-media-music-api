package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

import tfg.socialmediamusicapi.dto.InstrumentoDtoGet;
import tfg.socialmediamusicapi.dto.InstrumentoDtoPost;
import tfg.socialmediamusicapi.service.InstrumentoService;

@RestController
@Api(tags = "Instrumento Api")
public class InstrumentoController {
    
    @Autowired
    private InstrumentoService service;
    
    @GetMapping("/instrumentos")
    @Operation(summary = "Recupera todos los instrumentos")
    public List<InstrumentoDtoGet> getAllUsuarios(){
	
	return service.getAllInstrumentos();
    }
    
    @GetMapping("/instrumentos/{id}")
    @Operation(summary = "Recupera un instrumento por id")
    public InstrumentoDtoGet finById(@PathVariable("id") long id) {
	
	return service.findById(id);
    }
    
    @PostMapping("/instrumentos")
    @Operation(summary = "Guarda un instrumento")
    public ResponseEntity<String> crearInstrumento(@RequestBody InstrumentoDtoPost instrumentoDto) {
	service.createInstrumento(instrumentoDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }


}
