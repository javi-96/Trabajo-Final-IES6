package ar.edu.ies6.tf.model;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.ies6.tf.util.Almacenamiento;
import ar.edu.ies6.tf.util.Ram;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import jakarta.persistence.OneToMany;
@Component
@Entity
public class Producto {
	
//atributos de un producto
	@Id
	private String id;
	@Column
	private String marca;
	@Column
	private String modelo;
	@Column
	private String descripcion;
	@Column
	private String stock;
	@Lob
	@Column (columnDefinition = "LONGTEXT")
	private String imagen;
	@Column
	private String origen;
	@Column
	private String color;
	@Column
	private Double precio;
	@Column
	private Boolean estado;
	@Column
	@Enumerated(EnumType.STRING)
	private Almacenamiento almacenamiento;
	@Column
	@Enumerated(EnumType.STRING)
	private Ram ram;
	
	@OneToMany(mappedBy = "producto")
    private List<Compra> compra;
	
	
	
	
	//constructor por defecto
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	//metodos accesores
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}


	public Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}


	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}


	public Ram getRam() {
		return ram;
	}


	public List<Compra> getCompra() {
		return compra;
	}


	public void setCompras(List<Compra> compra) {
		this.compra = compra;
	}



	public void setRam(Ram ram) {
		this.ram = ram;
	}
	
	
	
	
}
