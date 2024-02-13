package tfg.socialmediamusicapi.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@NoArgsConstructor
@Data
public class AsignarInteresDto {
    
    @JsonProperty(value="usuarioId")
    private Long usuarioId;
    
    
    @JsonProperty(value="interesId")
    private Long interesId;


    public Object getUsuarioId() {
	
	return usuarioId;
    }


    public Object getInteresId() {
	
	return interesId;
    }

}
