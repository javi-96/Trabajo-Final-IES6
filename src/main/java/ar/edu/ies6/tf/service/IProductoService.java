package ar.edu.ies6.tf.service;

import java.util.List;

import ar.edu.ies6.tf.model.Producto;

public interface IProductoService {


//metodos que resuelven una tarea
	
public void guardarProducto (Producto producto);
public void eliminarProducto(String dni);
public void modificarProducto(Producto productoModificado);
public Producto consultarProducto(String dni);
public List<Producto>listarTodosProductos();
public List<Producto>listarTodosProductosActivos();
}
