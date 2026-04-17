package com.microservicio1.entregaproductomascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ENVIOS")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El producto es obligatorio")
    @Column(nullable = false, length = 100)
    private String producto;

    @NotBlank(message = "El destinatario es obligatorio")
    @Column(nullable = false, length = 100)
    private String destinatario;

    @NotBlank(message = "La direccion es obligatoria")
    @Column(nullable = false, length = 200)
    private String direccion;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 30)
    private String estado;

    @NotBlank(message = "La ubicacion actual es obligatoria")
    @Column(name = "ubicacion_actual", nullable = false, length = 100)
    private String ubicacionActual;

    @Column(name = "fecha_estimada_entrega", length = 20)
    private String fechaEstimadaEntrega;

    public Envio() {
    }

    public Envio(Long id, String producto, String destinatario, String direccion,
                 String estado, String ubicacionActual, String fechaEstimadaEntrega) {
        this.id = id;
        this.producto = producto;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.estado = estado;
        this.ubicacionActual = ubicacionActual;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }

    public String getFechaEstimadaEntrega() { return fechaEstimadaEntrega; }
    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) { this.fechaEstimadaEntrega = fechaEstimadaEntrega; }
}