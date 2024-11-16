package ar.edu.ies6.tf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tf.model.Cliente;
import ar.edu.ies6.tf.service.IClienteService;

@Controller 
public class ClienteController {
	
	@Autowired
	Cliente cliente01; //un cliente es un objeto de la clase cliente y cliente es una clase java   
	
	@Autowired 
	@Qualifier("servicioClienteBD")
	IClienteService clienteService;
	//gestiona el acceso a la vista
	
	@GetMapping ("/Cliente")
	public ModelAndView getIndexWithCliente () {
		
		
		   ModelAndView trasportador = new ModelAndView ("listaCliente");
		   trasportador.addObject("Cliente", cliente01); //podria llamarlo directamente del almacen de alumnos pero treria problemas  //porque no sabemos que tiene el almacen, nos traeria un problema de segurida..entonces le dejamos al service que haga ese trabajo
	
		   return trasportador;
	}
	
	
	@PostMapping ("/guardarCliente")
	public ModelAndView guardarCliente (Cliente cliente) {
		
		// clienteServiceImp  clienteService = new AlumnoServiceImp (); /////////////////////no me quiere tomar la interfaz de alumno////////////////////////////////
		clienteService.guardarCliente(cliente);
		
		ModelAndView transportador = new ModelAndView ("listarCliente");
		transportador.addObject("listadoCliente", clienteService.listarTodosClientesActivos());
		
		return transportador;
	}
	
	//eliminar cliente
	@GetMapping("/eliminarCliente/{dni}")
	public ModelAndView deleteCliente(@PathVariable String dni) {
		clienteService.eliminarCliente(dni);
	
	//mostra el nuevo listado
	    ModelAndView modelView = new ModelAndView("listaCliente");
	    modelView.addObject("listadoCliente", clienteService.listarTodosClientesActivos());
	      
	      return modelView;
	}
	
	//modificar 
	@GetMapping("/modificarCliente/{dni}")
	public ModelAndView modificarCliente(@PathVariable String dni) {
		//el parametro del contructor del modelAndView es una vista HTML
	
		ModelAndView modelView = new ModelAndView("listaCliente");
		modelView.addObject("clienteAModificar", clienteService.consultarCliente(dni));
		modelView.addObject("band", false);
		
		return modelView;

	}

}
