package ar.edu.ies6.tf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tf.model.Compra;
import ar.edu.ies6.tf.service.ICompraService;


public class CompraController {
	@Autowired
	Compra unaCompra;

    @Autowired
    @Qualifier ("servicioCompraBD")
   ICompraService compraService;
	
	@GetMapping ("/compra")
	public ModelAndView getIndexWithCompra ( ) {
		 ModelAndView transportador = new ModelAndView	("compra");
		 transportador.addObject("compra",unaCompra); 
		 transportador.addObject("band",true);
	return transportador;     
}
	@GetMapping ("listadoCompra") 
	public ModelAndView getAllCompra () {
		//codigo
		//tranporte  hacia la Vista
		 ModelAndView transportador = new ModelAndView ("listadoCompra");
		transportador.addObject("listado",compraService.listarTodasComprasActivas()); 
		      
		return transportador;     
	}
	
	@PostMapping ("/guardarCompra")
	public ModelAndView guardarCompra (Compra compra) {
		
	// CompraServiceImp  compraService = new CompraServiceImp (); /////////////////////no me quiere tomar la interfaz de alumno////////////////////////////////
	   compraService.guardarCompra(compra);
		
	   ModelAndView transportador = new ModelAndView ("listaAlumnos");
	   transportador.addObject("listadoAlumnos" ,compraService.listarTodasComprasActivas());                 //podria llamarlo directamente del almacen de alumnos pero treria problemas 
		return transportador;		          
}
	//eliminar Compra
	@GetMapping("/eliminarCompra/{dni}")
	public ModelAndView deleteCompra(@PathVariable String dni) {
	//system.out.println.("este es el codigo:  "+codigo);
		compraService.eliminarCompra(dni);
		
 //mostra el nuevo listado
		ModelAndView modelView = new ModelAndView("listaCompras");
		  modelView.addObject("listadoCompras" ,compraService.listarTodasComprasActivas());
		  return modelView;
	}
	
	//modificaar
	@GetMapping("/modificarAlumno/{dni}")
	public ModelAndView modificarCompra(@PathVariable String dni) {
	//el parametro del contructor del modelAndView es una vista HTML
		ModelAndView modelView = new ModelAndView("listaCompra");
		modelView.addObject("compraAModificar",compraService.consultarCompra(dni));
		modelView.addObject("band",false);
		  return modelView;
	
}
}
