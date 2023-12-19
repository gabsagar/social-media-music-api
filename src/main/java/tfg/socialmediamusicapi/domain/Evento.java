package tfg.socialmediamusicapi.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;





@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "EVENTO")
public class Evento implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID",nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "TITULO",nullable = false)
    private String titulo;
    
    @Column(name = "FECHA",nullable = false)
    private LocalDateTime fecha;
    
    @Column(name = "DIRECCION",nullable = false)
    private String direccion;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Column(name = "FOTO")
    private String foto;
    
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
   /* @ManyToMany(mappedBy = "eventos", fetch = FetchType.LAZY)
    private List<Instrumento> instrumentos; */
    
    @OneToMany(mappedBy = "evento")
    private List<EventoInstrumento> registros;
    
    

}
