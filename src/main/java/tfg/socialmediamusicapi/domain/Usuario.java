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
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class Usuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false, updatable = false)
    @Id
    private long id;
    
    @Column(name = "NOMBRE",nullable = false)
    private String nombre;
    
    @Column(name = "CIUDAD",nullable = false)
    private String ciudad;
    
    @Column(name = "AGRUPACION")
    private String agrupacion;
    
    @Column(name = "FOTO")
    private String foto;
    
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_EVENTO", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns  = @JoinColumn(name = "EVENTO_ID"))
    private List<Evento> eventos;
    
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<Interes> intereses;
    
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<Instrumento> instrumentos;
}
