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


@Controller
public class ProductoController {
	
	//aqui lo primero que hace es instanciar por unica vez a producto.
		//esto es una inyeccion de dependencia
@Autowired
Producto unProducto; //unProducto es un objeto de la clase producto. Producto es una clase java
@Qualifier ("servicioProductoBD")
@Autowired
IProductoService productoService;

//recibe las peticiones de producto

@GetMapping ("/producto")
public ModelAndView getIndexWithProducto() {

ModelAndView transportador = new ModelAndView ("Producto");
transportador.addObject("Producto", unProducto);
transportador.addObject("band", false);

return transportador;
}
//luego que se cargan los datos viene por post
@PostMapping("/guardarProducto")
public ModelAndView guardarProducto (Producto producto) {
	//aqui creamos el servi y mandamos a guardar perroo es un problema asi que creamos una
		//inyeccion de dependenci a
		//DocenteServiceImp docenteService = new DocenteServiceImp() ;
productoService.guardarProducto (producto);

ModelAndView transportador = new ModelAndView("listaProductos");
transportador.addObject("listadoProductos", productoService.listarTodosProductosActivos()); //estamos llamando al almacen de docentes
return transportador;
}
//Eliminar docente
@GetMapping("/eliminarProducto/{id}") 
public ModelAndView deleteProducto (@PathVariable (name = "id") String id) {
productoService.eliminarProducto(id);

//muestra el nuevo listado
ModelAndView modelView = new ModelAndView("listaProductos");
modelView.addObject("listadoProductos", productoService.listarTodosProductosActivos());
return modelView;
}
//Modificar
@GetMapping("/modificarProducto/{id}") 
public ModelAndView modificarProducto (@PathVariable(name = "id") String id) {
	//el parametro ModelAndView es la vista html
	ModelAndView modelView = new ModelAndView("producto");
	
	modelView.addObject("producto", productoService.consultarProducto(id) );
	modelView.addObject("band", true);
	
	return modelView;
}
@GetMapping("/listadoproductos") 
public ModelAndView getAllProducto () {

ModelAndView transportador = new ModelAndView("ListadoProductos");
transportador.addObject("listado", productoService.listarTodosProductosActivos());

return transportador;}


}
	
	

