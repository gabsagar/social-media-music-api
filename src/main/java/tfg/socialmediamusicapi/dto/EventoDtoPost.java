package tfg.socialmediamusicapi.dto;

import java.time.LocalDateTime;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class EventoDtoPost {

    @JsonProperty(value = "titulo")
    private String titulo;

    @JsonProperty(value = "fecha")
    private LocalDateTime fecha;

    @JsonProperty(value = "tipo")
    private String tipo;
    
    @JsonProperty(value = "direccion")
    private String direccion;

}
