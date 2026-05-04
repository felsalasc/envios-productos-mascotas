package com.microservicio1.entregaproductomascotas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.microservicio1.entregaproductomascotas.model.Envio;
import com.microservicio1.entregaproductomascotas.repository.EnvioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setId(1L);
        envio.setProducto("Alimento para perro");
        envio.setDestinatario("Carlos Mendez");
        envio.setDireccion("Av. Siempre Viva 123");
        envio.setEstado("EN_TRANSITO");
        envio.setUbicacionActual("Santiago");
        envio.setFechaEstimadaEntrega("2026-05-10");
    }

    @Test
    void testListarTodos() {
        List<Envio> expected = Arrays.asList(envio);

        when(envioRepository.findAll()).thenReturn(expected);

        List<Envio> resultado = envioService.listarTodos();

        assertEquals(expected, resultado);
        verify(envioRepository).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Optional<Envio> resultado = envioService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(envio, resultado.get());
        verify(envioRepository).findById(1L);
    }

    @Test
    void testBuscarPorEstado() {
        List<Envio> expected = Arrays.asList(envio);

        when(envioRepository.findByEstadoIgnoreCase("EN_TRANSITO")).thenReturn(expected);

        List<Envio> resultado = envioService.buscarPorEstado("EN_TRANSITO");

        assertEquals(expected, resultado);
        verify(envioRepository).findByEstadoIgnoreCase("EN_TRANSITO");
    }

    @Test
    void testGuardarEnvio() {
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.guardar(envio);

        assertEquals(envio, resultado);
        verify(envioRepository).save(envio);
    }

    @Test
    void testActualizarEnvioExistente() {
        Envio datos = new Envio();
        datos.setProducto("Arena para gato");
        datos.setDestinatario("Ana Torres");
        datos.setDireccion("Calle Norte 456");
        datos.setEstado("ENTREGADO");
        datos.setUbicacionActual("Valparaiso");
        datos.setFechaEstimadaEntrega("2026-05-12");

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));
        when(envioRepository.save(envio)).thenReturn(envio);

        Optional<Envio> resultado = envioService.actualizar(1L, datos);

        assertTrue(resultado.isPresent());
        assertEquals("Arena para gato", resultado.get().getProducto());
        assertEquals("Ana Torres", resultado.get().getDestinatario());
        assertEquals("Calle Norte 456", resultado.get().getDireccion());
        assertEquals("ENTREGADO", resultado.get().getEstado());
        assertEquals("Valparaiso", resultado.get().getUbicacionActual());
        assertEquals("2026-05-12", resultado.get().getFechaEstimadaEntrega());

        verify(envioRepository).save(envio);
    }

    @Test
    void testActualizarEnvioNoExistente() {
        Envio datos = new Envio();

        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Envio> resultado = envioService.actualizar(1L, datos);

        assertTrue(resultado.isEmpty());
        verify(envioRepository, never()).save(any());
    }

    @Test
    void testEliminarEnvioExistente() {
        when(envioRepository.existsById(1L)).thenReturn(true);

        boolean resultado = envioService.eliminar(1L);

        assertTrue(resultado);
        verify(envioRepository).deleteById(1L);
    }

    @Test
    void testEliminarEnvioNoExistente() {
        when(envioRepository.existsById(1L)).thenReturn(false);

        boolean resultado = envioService.eliminar(1L);

        assertFalse(resultado);
        verify(envioRepository, never()).deleteById(anyLong());
    }
}