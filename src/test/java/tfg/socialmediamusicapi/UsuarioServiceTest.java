
package tfg.socialmediamusicapi;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tfg.socialmediamusicapi.domain.Evento;
import tfg.socialmediamusicapi.domain.Instrumento;
import tfg.socialmediamusicapi.domain.Interes;
import tfg.socialmediamusicapi.domain.Usuario;
import tfg.socialmediamusicapi.dto.UsuarioDtoGet;
import tfg.socialmediamusicapi.dto.UsuarioDtoPost;
import tfg.socialmediamusicapi.dto.UsuarioDtoPut;
import tfg.socialmediamusicapi.repository.InteresRepository;
import tfg.socialmediamusicapi.repository.UsuarioRepository;
import tfg.socialmediamusicapi.service.UsuarioService;

@SpringBootTest
class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;

    @MockBean
    private InteresRepository repositoryInteres;

    @BeforeEach
    void setup() {

    }

    @Test
    public void findByIdTest() {

	long id = 1;
	List<Interes> intereses = new ArrayList<>();
	List<Evento> eventos = new ArrayList<>();
	List<Instrumento> instrumentos = new ArrayList<>();

	Usuario usuario1 = new Usuario(id, "Gabriel", "Valencia", "Tendetes", intereses, eventos, instrumentos);
	when(repository.findById(id)).thenReturn(Optional.of(usuario1));

	UsuarioDtoGet usuario = service.findById(id);

	assertEquals(id, usuario.getId());
	System.out.println("Usuario:" + usuario);

    }

    @Test
    public void createUsuarioTest() {
	UsuarioDtoPost usuarioDto = new UsuarioDtoPost("Gabriel", "Valencia", "Tendetes");

	service.createUsuario(usuarioDto);

	verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void modificarUsuarioTest() {
	long id = 1;
	List<Interes> intereses = new ArrayList<>();
	List<Evento> eventos = new ArrayList<>();
	List<Instrumento> instrumentos = new ArrayList<>();

	Usuario usuario1 = new Usuario(id, "Gabriel", "Valencia", "Tendetes", intereses, eventos, instrumentos);
	when(repository.findById(id)).thenReturn(Optional.of(usuario1));

	UsuarioDtoPut usuarioDto = new UsuarioDtoPut("NuevoNombre", "NuevaCiudad", "NuevaAgrupacion");

	service.modificarUsuario(id, usuarioDto);

	verify(repository, times(1)).findById(id);

	verify(repository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void modificarUsuarioConUsuarioNoExistenteTest() {

	long idNoExistente = 2;
	UsuarioDtoPut usuarioDtoNoExistente = new UsuarioDtoPut("NuevoNombre", "NuevaCiudad", "NuevaAgrupacion");

	assertThrows(NoSuchElementException.class, () -> {
	    service.modificarUsuario(idNoExistente, usuarioDtoNoExistente);
	});

	verify(repository, times(1)).findById(idNoExistente);
	verify(repository, times(0)).save(any(Usuario.class));
    }

    @Test
    public void eliminarUsuarioTest() {
	long idExistente = 1;

	List<Interes> intereses = new ArrayList<>();
	List<Evento> eventos = new ArrayList<>();
	List<Instrumento> instrumentos = new ArrayList<>();

	Usuario usuarioExistente = new Usuario(idExistente, "Gabriel", "Valencia", "Tendetes", intereses, eventos,
		instrumentos);
	when(repository.findById(idExistente)).thenReturn(Optional.of(usuarioExistente));

	service.eliminarUsuario(idExistente);

	verify(repository, times(1)).findById(idExistente);

	verify(repository, times(1)).delete(usuarioExistente);

	assertTrue(usuarioExistente.getIntereses().isEmpty());
	assertTrue(usuarioExistente.getEventos().isEmpty());
	assertTrue(usuarioExistente.getInstrumentos().isEmpty());
    }

    @Test
    public void eliminarUsuarioNoExistenteTest() {
	long idNoExistente = 2;

	when(repository.findById(idNoExistente)).thenReturn(Optional.empty());

	assertThrows(NoSuchElementException.class, () -> {
	    service.eliminarUsuario(idNoExistente);
	});

	verify(repository, times(1)).findById(idNoExistente);

	verify(repository, times(0)).delete(any(Usuario.class));
    }

}
