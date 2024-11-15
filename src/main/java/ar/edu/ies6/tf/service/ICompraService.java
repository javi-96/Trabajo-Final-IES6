package ar.edu.ies6.tf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.ies6.tf.model.Compra;

@Service
public interface ICompraService {

	//metodos que resuelven una tarea
	public void guardarCompra (Compra compra);
	public void eliminarCompra(String dni);
	public void modificarCompra(Compra compraModificada);
	public Compra consultarCompra(String dni);
	public List<Compra> listarTodasCompras();
	public List<Compra>listarTodasComprasActivas();
	
	
}
