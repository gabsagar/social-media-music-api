package tfg.socialmediamusicapi.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class InteresDto {
   
    @JsonProperty(value = "id")
    private long id;
    
    @JsonProperty(value="nombre")
    private String nombre;
    

}
