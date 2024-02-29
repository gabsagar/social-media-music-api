package tfg.socialmediamusicapi.dto;


import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Validated
@NoArgsConstructor
@Data
public class UsuarioDtoGet {
        
    @JsonProperty(value = "id")
    private long id;
    
    @JsonProperty(value="nombre")
    private String nombre;
    
    @JsonProperty(value="ciudad")
    private String ciudad;
    
    @JsonProperty(value = "agrupacion")
    private String agrupacion;

    public Long getId() {
	return id;
    }
    
    public UsuarioDtoGet(long id2, String nombre, String ciudad) {
        this.id = id2;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }
    
   
}
