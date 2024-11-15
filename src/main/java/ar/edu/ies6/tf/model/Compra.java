package ar.edu.ies6.tf.model;

import java.time.LocalDate;

public class Compra {
	
//crear los atributos
	
	private String Id ;
	private LocalDate fechaCompra;
	private int cantidad ;
	private String formaPago;
	private String DomicilioEntrega;
	private Boolean  estado;
 
//constructor por defecto
public Compra() {
	
}

public String getId() {
	return Id;
}

public void setId(String id) {
	Id = id;
}

public LocalDate getFechaCompra() {
	return fechaCompra;
}

public void setFechaCompra(LocalDate fechaCompra) {
	this.fechaCompra = fechaCompra;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public String getFormaPago() {
	return formaPago;
}

public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}

public String getDomicilioEntrega() {
	return DomicilioEntrega;
}

public void setDomicilioEntrega(String domicilioEntrega) {
	DomicilioEntrega = domicilioEntrega;
}

public Boolean getEstado() {
	return estado;
}

public void setEstado(Boolean estado) {
	this.estado = estado;
}



}