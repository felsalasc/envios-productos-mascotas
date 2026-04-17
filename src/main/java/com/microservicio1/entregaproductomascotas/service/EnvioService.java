package com.microservicio1.entregaproductomascotas.service;

import com.microservicio1.entregaproductomascotas.model.Envio;
import com.microservicio1.entregaproductomascotas.repository.EnvioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> buscarPorId(Long id) {
        return envioRepository.findById(id);
    }

    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstadoIgnoreCase(estado);
    }

    public Envio guardar(Envio envio) {
        return envioRepository.save(envio);
    }

    public Optional<Envio> actualizar(Long id, Envio datos) {
        return envioRepository.findById(id).map(envio -> {
            envio.setProducto(datos.getProducto());
            envio.setDestinatario(datos.getDestinatario());
            envio.setDireccion(datos.getDireccion());
            envio.setEstado(datos.getEstado());
            envio.setUbicacionActual(datos.getUbicacionActual());
            envio.setFechaEstimadaEntrega(datos.getFechaEstimadaEntrega());
            return envioRepository.save(envio);
        });
    }

    public boolean eliminar(Long id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}