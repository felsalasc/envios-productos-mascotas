package com.microservicio1.entregaproductomascotas.model;


public class Envio {

    private Long id;
    private String producto;
    private String destinatario;
    private String direccion;
    private String estado;
    private String ubicacionActual;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }
}