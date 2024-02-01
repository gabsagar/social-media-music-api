package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tfg.socialmediamusicapi.dto.UsuarioDto;
import tfg.socialmediamusicapi.service.UsuarioService;

@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping("/usuarios")
    public List<UsuarioDto> getAllUsuarios(){
	
	return service.getAllUsuarios();
    }
    
    @GetMapping("/usuarios/{id}")
    public UsuarioDto finById(@PathVariable("id") long id) {
	
	return service.findById(id);
    }

}
