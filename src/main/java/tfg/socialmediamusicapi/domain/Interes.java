package tfg.socialmediamusicapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "INTERES")
public class Interes implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID",nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "NOMBRE",nullable = false)
    private String nombre;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "INTERES_USUARIO", joinColumns = @JoinColumn(name = "INTERES_ID"), inverseJoinColumns  = @JoinColumn(name = "USUARIO_ID"))
    private List<Usuario> usuarios;

}
