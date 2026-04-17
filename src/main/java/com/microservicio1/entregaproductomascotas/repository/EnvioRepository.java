package com.microservicio1.entregaproductomascotas.repository;

import com.microservicio1.entregaproductomascotas.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    List<Envio> findByEstadoIgnoreCase(String estado);
}