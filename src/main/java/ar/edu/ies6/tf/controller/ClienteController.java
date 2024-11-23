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
import ar.edu.ies6.tf.util.Departamento;
import ar.edu.ies6.tf.util.Localidad;

@Controller 
public class ClienteController {
	
	@Autowired
	Cliente cliente01; //un cliente es un objeto de la clase cliente y cliente es una clase java   
	
	
	@Qualifier("servicioClienteBD")
	@Autowired 
	IClienteService clienteService;
	//gestiona el acceso a la vista
	
	@GetMapping ("/registroCliente")
	public ModelAndView getIndexWithCliente () {
		
		
		   ModelAndView transportador = new ModelAndView ("registroCliente");
		   transportador.addObject("cliente", cliente01); //podria llamarlo directamente del almacen de alumnos pero treria problemas  //porque no sabemos que tiene el almacen, nos traeria un problema de segurida..entonces le dejamos al service que haga ese trabajo
								//nombre de la vista 	//enum
		   transportador.addObject("departamentos", Departamento.values());
		   transportador.addObject("localidades", Localidad.values() );
		   transportador.addObject("band", false);
		   return transportador;
	}
	
	
	@PostMapping ("/guardarCliente")
	public ModelAndView guardarCliente (Cliente cliente) {
		
		// clienteServiceImp  clienteService = new AlumnoServiceImp (); /////////////////////no me quiere tomar la interfaz de alumno////////////////////////////////
		clienteService.guardarCliente(cliente);
		
		ModelAndView transportador = new ModelAndView ("listaCliente");
		transportador.addObject("listadoCliente", clienteService.listarTodosClientesActivos());
		
		return transportador;
	}
	
	//eliminar cliente
	@GetMapping("/eliminarCliente/{dni}")
	public ModelAndView deleteCliente(@PathVariable (name = "dni")String dni) {
		clienteService.eliminarCliente(dni);
	
	//mostra el nuevo listado
	    ModelAndView modelView = new ModelAndView("listaCliente");
	    modelView.addObject("listadoCliente", clienteService.listarTodosClientesActivos());
	      
	      return modelView;
	}
	
	//modificar 
	@GetMapping("/modificarCliente/{dni}")
	public ModelAndView modificarCliente(@PathVariable (name = "dni") String dni) {
		//el parametro del contructor del modelAndView es una vista HTML
	
		ModelAndView modelView = new ModelAndView("registroCliente");
		modelView.addObject("cliente", clienteService.consultarCliente(dni));
		modelView.addObject("band", true);
		modelView.addObject("departamentos", Departamento.values());
		modelView.addObject("localidades", Localidad.values() );
		return modelView;

	}
	
	@GetMapping("/listadoClientes") 
	public ModelAndView getAllProducto () {
                                                     //vista html
	ModelAndView transportador = new ModelAndView("listaCliente");
	transportador.addObject("listadoCliente", clienteService.listarTodosClientesActivos());

	return transportador;}
	
	


}
