package tfg.socialmediamusicapi.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class EventoDtoPut {

    @JsonProperty(value = "titulo")
    private String titulo;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonProperty(value = "fecha")
    private LocalDateTime fecha;

    @JsonProperty(value = "tipo")
    private String tipo;

    @JsonProperty(value = "direccion")
    private String direccion;

    public String getTitulo() {
	
	return titulo;
    }

    public LocalDateTime getFecha() {
	
	return fecha;
    }

    public String getDireccion() {
	
	return direccion;
    }

}
