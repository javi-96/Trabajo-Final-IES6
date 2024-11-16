package ar.edu.ies6.tf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.tf.model.Cliente;

@Service
public interface IClienteService {

	//metodos que resuelven una tarea
	public void guardarCliente (Cliente cliente);
	public void eliminarCliente(String dni);
	public void modificarCliente(Cliente clienteModificado);
	public Cliente consultarCliente(String dni);
	public List<Cliente>listarTodosClientes();
	public List<Cliente>listarTodosClientesActivos();
	
	
}
