package tfg.socialmediamusicapi.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class UsuarioDtoPost {

    @JsonProperty(value = "nombre")
    private String nombre;

    @JsonProperty(value = "ciudad")
    private String ciudad;

    @JsonProperty(value = "agrupacion")
    private String agrupacion;
    
    public UsuarioDtoPost(String nombre, String ciudad, String agrupacion) {
       
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.agrupacion = agrupacion;
    }

    public String getNombre() {
	
	return nombre;
    }
    
    public String getCiudad() {

	return ciudad;
    }


    public String getAgrupacion() {

	return agrupacion;
    }

}
