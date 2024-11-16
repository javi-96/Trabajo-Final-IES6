package ar.edu.ies6.tf.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.ies6.tf.model.Producto;
import ar.edu.ies6.tf.repository.ProductoRepository;
import ar.edu.ies6.tf.service.IProductoService;



public class ProductoServiceImpBD implements IProductoService{

	@Autowired
	ProductoRepository productoRepository;
	
	@Override
	public void guardarProducto(Producto producto) {
		// TODO Auto-generated method stub
		//aqui guarda en la base de datos
		producto.setEstado(true);
		productoRepository.save(producto);

		
	}

	@Override
	public void eliminarProducto(String id) {
		// TODO Auto-generated method stub
		Optional<Producto> productoEncontrado = productoRepository.findById(id);
		productoEncontrado.get().setEstado(false);
		productoRepository.save(productoEncontrado.get());	
		
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto consultarProducto(String id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

	@Override
	public List<Producto> listarTodosProductos() {
		// TODO Auto-generated method stub
		return (List <Producto>) productoRepository.findAll();
	}

	@Override
	public List<Producto> listarTodosProductosActivos() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoRepository.findByEstado(true); //muestra todo los q estan activos
	}

	
}
