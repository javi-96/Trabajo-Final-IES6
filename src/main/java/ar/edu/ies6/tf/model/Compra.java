package ar.edu.ies6.tf.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
@Component 
@Entity
public class Compra {
	
//crear los atributos
	
	@Id
	private String idCompra;
	@Column
	private LocalDate fechaCompra;
	@Column
	private Integer cantidad ;
	@Column
	private String formaPago;
	@Column
	private String domicilioEntrega;
	@Column
	private Boolean  estado;
	
	
	@ManyToOne
	private Producto productos;
	@ManyToOne
	private Cliente clientes;
  
	
 
 
//constructor por defecto
public Compra() {
	
}

public String getIdCompra() {
	return idCompra;
}

public void setIdCompra(String idCompra) {
	this.idCompra = idCompra;
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

public void setCantidad(Integer cantidad) {
	this.cantidad = cantidad;
}

public String getFormaPago() {
	return formaPago;
}

public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}

public String getDomicilioEntrega() {
	return domicilioEntrega;
}

public void setDomicilioEntrega(String domicilioEntrega) {
	this.domicilioEntrega = domicilioEntrega;
}

public Boolean getEstado() {
	return estado;
}

public void setEstado(Boolean estado) {
	this.estado = estado;
}



}