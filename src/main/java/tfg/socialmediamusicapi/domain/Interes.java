package tfg.socialmediamusicapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "INTERES")
public class Interes implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false, updatable = false)
    @Id
    private long id;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;
    
    @ManyToMany(mappedBy = "intereses", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
	
	return usuarios;
    }
    
  

}