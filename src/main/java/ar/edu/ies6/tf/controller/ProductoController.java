package ar.edu.ies6.tf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tf.model.Producto;
import ar.edu.ies6.tf.service.IProductoService;
import ar.edu.ies6.tf.util.Almacenamiento;
import ar.edu.ies6.tf.util.Ram;


@Controller
public class ProductoController {
	
	//aqui lo primero que hace es instanciar por unica vez a producto.
		//esto es una inyeccion de dependencia
@Autowired
Producto unProducto; //unProducto es un objeto de la clase producto. Producto es una clase java

@Qualifier ("servicioProductoBD")
@Autowired
IProductoService productoService;

//recibe las peticiones de listaproducto

@GetMapping ("/registroProducto")
public ModelAndView getIndexWithProducto() {

ModelAndView transportador = new ModelAndView ("registroProducto");
transportador.addObject("producto", unProducto);
transportador.addObject("band", true);

return transportador;
}

//luego que se cargan los datos viene por post
@PostMapping("/guardarProducto")
public ModelAndView guardarProducto (Producto producto) {
productoService.guardarProducto (producto);

ModelAndView transportador = new ModelAndView("listaProducto");
transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos()); //estamos llamando al almacen de docentes
return transportador;
}


//Eliminar producto
@GetMapping("/eliminarProducto/{id}") 
public ModelAndView deleteProducto (@PathVariable (name = "id") String id) {
productoService.eliminarProducto(id);

//muestra el nuevo listado
ModelAndView modelView = new ModelAndView("listaProducto");
modelView.addObject("listadoProducto", productoService.listarTodosProductosActivos());
return modelView;
}


//Modificar
@GetMapping("/modificarProducto/{id}") 
public ModelAndView modificarProducto (@PathVariable(name = "id") String id) {
	//el parametro ModelAndView es la vista html
	ModelAndView modelView = new ModelAndView("producto");
	
	modelView.addObject("productoModificar", productoService.consultarProducto(id) );
	modelView.addObject("band", true);
	
	return modelView;
}
@GetMapping("/listadoProductos") 
public ModelAndView getAllProducto () {

ModelAndView transportador = new ModelAndView("listaProducto");
transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());

return transportador;}


}	

