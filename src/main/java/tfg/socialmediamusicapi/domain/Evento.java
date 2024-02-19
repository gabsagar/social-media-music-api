package tfg.socialmediamusicapi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "EVENTO")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ID", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "FECHA", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "DIRECCION", nullable = false)
    private String direccion;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FOTO")
    private String foto;
    
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "EVENTO_INSTRUMENTO", joinColumns = @JoinColumn(name = "EVENTO_ID"), inverseJoinColumns = @JoinColumn(name = "INSTRUMENTO_ID"))
    private List<Instrumento> instrumentos;

    public List<Usuario> getUsuarios() {
	
	return usuarios;
    }

    public void setTitulo(String titulo) {
	this.titulo = titulo;
	
    }

    public void setFecha(LocalDateTime fecha) {
	this.fecha = fecha;
	
    }

    public void setDireccion(String direccion) {
	this.direccion = direccion;
	
    }

    public List<Instrumento> getInstrumentos() {
	
	return instrumentos;
    }
    
   
   

}
