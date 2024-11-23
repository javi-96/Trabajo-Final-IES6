package ar.edu.ies6.tf.controller;


import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
transportador.addObject("band", false);

return transportador;
}


//luego que se cargan los datos viene por post
@PostMapping(value="/guardarProducto", consumes="multipart/form-data")
public ModelAndView guardarProducto (Producto producto, @RequestParam ("file") MultipartFile [] imagen) {
ModelAndView transportador = new ModelAndView("listaProducto");

try{
	byte [] contenido=imagen [0].getBytes();
	String base64= Base64.getEncoder().encodeToString(contenido);
	producto.setImagen(base64);
	productoService.guardarProducto (producto);
} catch
(Exception e) {
transportador.addObject("Error", e.getMessage()); 	
}
transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());
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
	
	ModelAndView modelView = new ModelAndView("registroProducto");
	
	modelView.addObject("producto", productoService.consultarProducto(id) );
	modelView.addObject("band", true);
	
	return modelView;
}


@GetMapping("/listadoProducto") 
public ModelAndView getAllProducto () {

ModelAndView transportador = new ModelAndView("listaProducto");
transportador.addObject("listadoProducto", productoService.listarTodosProductosActivos());

return transportador;
}


}	

