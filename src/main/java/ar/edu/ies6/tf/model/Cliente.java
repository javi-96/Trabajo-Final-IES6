package ar.edu.ies6.tf.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.ies6.tf.util.Departamento;
import ar.edu.ies6.tf.util.Localidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Component 
@Entity
public class Cliente {

	
		@Id
	private String dni;
		@Column
	private String nombre;
		@Column
	private String apellido;
		@Column
	private String correo;
		@Column
	private LocalDate fechaNacimiento;
		@Column 
		@Enumerated (EnumType.STRING) //se guarda el nombre del enum como texto en BD
	private Localidad localidad;
		@Column
		@Enumerated (EnumType.STRING)
	private Departamento departamento;
		@Column
	private Boolean estado;
		
		
		//Relacion con compras
		@OneToMany(mappedBy = "cliente")
		private List<Compra> compra;
			

	public Cliente() {
		// TODO Auto-generated constructor stub
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Boolean getEstado() {
		return estado;
	}


	public void setEstado(Boolean estado) {
		this.estado = estado;
	}


	public Localidad getLocalidad() {
		return localidad;
	}


	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
		


	public List<Compra> getCompra() {
		return compra;
	}


	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	
	
	
	
	
}
