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
    @Column(name = "ID", nullable = false, updatable = false)
    @Id
    private long id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CIUDAD", nullable = false)
    private String ciudad;

    @Column(name = "AGRUPACION")
    private String agrupacion;

    @Column(name = "FOTO")
    private String foto;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_INTERES", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns = @JoinColumn(name = "INTERES_ID"))
    private List<Interes> intereses;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_EVENTO", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns  = @JoinColumn(name = "EVENTO_ID"))
    private List<Evento> eventos;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_INSTRUMENTO", joinColumns = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns  = @JoinColumn(name = "INSTRUMENTO_ID"))
    private List<Instrumento> instrumentos;

   
    public Usuario(long id2, String nombre, String ciudad, String agrupacion, List<Interes> intereses, List<Evento> eventos, List<Instrumento> instrumentos) {
        this.id = id2;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.agrupacion = agrupacion;
        this.intereses = intereses;
        this.eventos = eventos;
        this.instrumentos = instrumentos;
    }

    public List<Interes> getIntereses() {

	return intereses;
    }

    public String getNombre() {

	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;

    }

    public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
	
    }
    
    public void setAgrupacion(String agrupacion) {
	this.agrupacion = agrupacion;
	
    }

    public List<Evento> getEventos() {
	
	return eventos;
    }

    public List<Instrumento> getInstrumentos() {
	
	return instrumentos;
    }

}