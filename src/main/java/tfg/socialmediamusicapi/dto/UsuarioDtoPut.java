package tfg.socialmediamusicapi.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class UsuarioDtoPut {

    @JsonProperty(value = "nombre")
    private String nombre;

    @JsonProperty(value = "ciudad")
    private String ciudad;

    @JsonProperty(value = "agrupacion")
    private String agrupacion;

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
