package com.microservicio1.entregaproductomascotas.controller;

import com.microservicio1.entregaproductomascotas.model.Envio;
import com.microservicio1.entregaproductomascotas.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> obtenerTodos() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return envioService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró el envío con id " + id)));
    }

    @GetMapping("/{id}/ubicacion")
    public ResponseEntity<?> obtenerUbicacion(@PathVariable Long id) {
        return envioService.buscarPorId(id)
                .<ResponseEntity<?>>map(envio -> ResponseEntity.ok(Map.of(
                        "id", envio.getId(),
                        "estado", envio.getEstado(),
                        "ubicacionActual", envio.getUbicacionActual()
                )))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró el envío con id " + id)));
    }

    @GetMapping("/estado/{estado}")
    public List<Envio> obtenerPorEstado(@PathVariable String estado) {
        return envioService.buscarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Envio> crear(@Valid @RequestBody Envio envio) {
        Envio nuevo = envioService.guardar(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Envio envio) {
        return envioService.actualizar(id, envio)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "No se encontró el envío con id " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (envioService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Envío eliminado correctamente"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "No se encontró el envío con id " + id));
    }
}