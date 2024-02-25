package tfg.socialmediamusicapi.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.dto.EventoDtoGet;
import tfg.socialmediamusicapi.dto.EventoDtoPost;
import tfg.socialmediamusicapi.dto.EventoDtoPut;
import tfg.socialmediamusicapi.dto.mapper.EventoMapper;
import tfg.socialmediamusicapi.repository.EventoRepository;
import tfg.socialmediamusicapi.repository.InstrumentoRepository;
import tfg.socialmediamusicapi.service.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository repository;

    @Autowired
    private InstrumentoRepository repositoryInstrumento;

    @Autowired
    private EventoMapper mapper;

    String message = "El id no existe";

    @Override
    public List<EventoDtoGet> getAllEventos() {

	List<Evento> eventos = repository.findAll();

	return mapper.fromDtoList(eventos);

    }

    @Override
    public EventoDtoGet findById(long id) {

	Evento evento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El evento con ID " + id + " no existe"));

	return mapper.fromEnity(evento);
    }

    @Override
    public void createEvento(EventoDtoPost eventoDto) {

	Evento evento = mapper.fromDtoPost(eventoDto);

	repository.save(evento);

    }

    @Override
    public List<EventoDtoGet> findByTipo(String tipo) {
        List<Evento> eventos = repository.findByTipo(tipo);

        if (eventos.isEmpty()) {
            throw new NoSuchElementException("No se encontraron eventos con tipo: " + tipo);
        }

        return mapper.fromDtoList(eventos);
    }


    @Override
    public List<EventoDtoGet> findByFecha(LocalDateTime fecha) {
        List<Evento> eventos = repository.findByFecha(fecha);

        if (eventos.isEmpty()) {
            throw new NoSuchElementException("No se encontraron eventos para la fecha: " + fecha);
        }

        return mapper.fromDtoList(eventos);
    }


    @Override
    public void modificarEvento(long id, EventoDtoPut eventoDto) {
	Evento evento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El evento con ID " + id + " no existe"));

	evento.setTitulo(eventoDto.getTitulo());
	evento.setFecha(eventoDto.getFecha());
	evento.setDireccion(eventoDto.getDireccion());
	repository.save(evento);

	repository.save(evento);

    }

    @Override
    public void eliminarEvento(long id) {
	Evento evento = repository.findById(id)
		.orElseThrow(() -> new NoSuchElementException("El evento con ID " + id + " no existe"));

	evento.getUsuarios().forEach(usuario -> usuario.getEventos().remove(evento));
	evento.getUsuarios().clear();

	evento.getInstrumentos().forEach(instrumento -> instrumento.getEventos().remove(evento));
	evento.getInstrumentos().clear();

	repository.delete(evento);

    }

    @Override
    public void agregarInstrumentoEvento(long eventoId, long instrumentoId) {
        Evento evento = repository.findById(eventoId)
                .orElseThrow(() -> new NoSuchElementException("El evento con ID " + eventoId + " no existe"));

        Instrumento instrumento = repositoryInstrumento.findById(instrumentoId)
                .orElseThrow(() -> new NoSuchElementException("El instrumento con ID " + instrumentoId + " no existe"));

        evento.getInstrumentos().add(instrumento);
        instrumento.getEventos().add(evento);

        repository.save(evento);
        repositoryInstrumento.save(instrumento);
    }

    

    @Override
    public List<EventoDtoGet> findEventosEstaSemana() {
        LocalDateTime hoy = LocalDateTime.now();

        LocalDateTime inicioSemana = hoy.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime finSemana = hoy.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<Evento> eventosEstaSemana = repository.findByFechaBetween(inicioSemana, finSemana);

        if (eventosEstaSemana.isEmpty()) {
            throw new NoSuchElementException("No se encontraron eventos para la semana actual");
        }

        return mapper.fromDtoList(eventosEstaSemana);
    }


    @Override
    public List<EventoDtoGet> findEventosFinDeSemana() {
        LocalDateTime hoy = LocalDateTime.now();

        LocalDateTime inicioFinDeSemana = hoy.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        LocalDateTime finFinDeSemana = inicioFinDeSemana.plusDays(2);

        List<Evento> eventosProximoFinDeSemana = repository.findByFechaBetween(inicioFinDeSemana, finFinDeSemana);

        if (eventosProximoFinDeSemana.isEmpty()) {
            throw new NoSuchElementException("No se encontraron eventos para el próximo fin de semana");
        }

        return mapper.fromDtoList(eventosProximoFinDeSemana);
    }


}
