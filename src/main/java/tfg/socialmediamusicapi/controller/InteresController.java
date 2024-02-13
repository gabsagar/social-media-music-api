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

import tfg.socialmediamusicapi.dto.InteresDtoGet;
import tfg.socialmediamusicapi.dto.InteresDtoPost;
import tfg.socialmediamusicapi.service.InteresService;

@RestController
@Api(tags = "Interes Api")
public class InteresController {
    
    @Autowired
    private InteresService service;
    
    @GetMapping("/intereses")
    @Operation(summary = "Recupera todos los intereses")
    public List<InteresDtoGet> getAllUsuarios(){
	
	return service.getAllIntereses();
    }
    
    @GetMapping("/intereses/{id}")
    @Operation(summary = "Recupera un interes por id")
    public InteresDtoGet finById(@PathVariable("id") long id) {
	
	return service.findById(id);
    }
    
    @PostMapping("/intereses")
    @Operation(summary = "Guarda un interes")
    public ResponseEntity<String> crearInteres(@RequestBody InteresDtoPost interesDto) {
	service.createInteres(interesDto);

	return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

}