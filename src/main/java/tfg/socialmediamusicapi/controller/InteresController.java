package tfg.socialmediamusicapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tfg.socialmediamusicapi.dto.InteresDto;
import tfg.socialmediamusicapi.service.InteresService;

@RestController
public class InteresController {
    
    @Autowired
    private InteresService service;
    
    @GetMapping("/intereses")
    public List<InteresDto> getAllUsuarios(){
	
	return service.getAllIntereses();
    }

}
