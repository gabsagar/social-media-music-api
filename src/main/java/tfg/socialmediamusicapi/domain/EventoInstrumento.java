package tfg.socialmediamusicapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "EVENTO_INSTRUMENTO")
public class EventoInstrumento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ID",nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento evento;
    
    @ManyToOne
    @JoinColumn(name="instrumento_id")
    private Instrumento instrumento;
    
    @Column(name = "CANTIDAD",nullable = false)
    private long cantidad;
    

}
