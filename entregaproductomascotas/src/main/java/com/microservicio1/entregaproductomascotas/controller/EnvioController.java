package com.microservicio1.entregaproductomascotas.controller;

import com.microservicio1.entregaproductomascotas.model.Envio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private List<Envio> listaEnvios = new ArrayList<>();

    public EnvioController() {
        listaEnvios.add(new Envio(
                1L,
                "Alimento para perro",
                "Felipe Salas",
                "mar atalantico 550",
                "PREPARANDO",
                "Bodega Central",
                "2026-04-01"
        ));

        listaEnvios.add(new Envio(
                2L,
                "Juguete para gato",
                "Maria perez",
                "Calle mar 456",
                "EN_TRANSITO",
                "Centro de Distribución",
                "2026-03-30"
        ));

        listaEnvios.add(new Envio(
                3L,
                "Correa para perro",
                "Pedro Ortega",
                "Pasaje luna  1",
                "ENTREGADO",
                "Domicilio del cliente",
                "2026-03-28"
        ));
    }

    @GetMapping
    public List<Envio> obtenerTodos() {
        return listaEnvios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        for (Envio envio : listaEnvios) {
            if (envio.getId().equals(id)) {
                return ResponseEntity.ok(envio);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No se encontró el envío con id " + id));
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<?> obtenerUbicacion(@PathVariable Long id) {
        for (Envio envio : listaEnvios) {
            if (envio.getId().equals(id)) {
                return ResponseEntity.ok(Map.of(
                        "id", envio.getId(),
                        "estado", envio.getEstado(),
                        "ubicacionActual", envio.getUbicacionActual()
                ));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No se encontró el envío con id " + id));
    }

    @GetMapping("/estado/{estado}")
    public List<Envio> obtenerPorEstado(@PathVariable String estado) {
        List<Envio> resultado = new ArrayList<>();

        for (Envio envio : listaEnvios) {
            if (envio.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(envio);
            }
        }

        return resultado;
    }

   
}