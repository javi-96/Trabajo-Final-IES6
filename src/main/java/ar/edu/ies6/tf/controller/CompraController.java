package ar.edu.ies6.tf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.ies6.tf.model.Cliente;
import ar.edu.ies6.tf.model.Compra;
import ar.edu.ies6.tf.model.Producto;
import ar.edu.ies6.tf.service.IClienteService;
import ar.edu.ies6.tf.service.ICompraService;
import ar.edu.ies6.tf.service.IProductoService;

@Controller
public class CompraController {
	@Autowired
	Compra unaCompra;

    @Autowired
    @Qualifier ("servicioCompraBD")
   ICompraService compraService;
    
    @Autowired
    IProductoService productoService;
    
    @Autowired
    IClienteService clienteService;
	
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
	
	@PostMapping("/guardarCompra")
    public ModelAndView guardarCompra(Compra compra) {
        ModelAndView modelAndView = new ModelAndView("recibo");
        try {
            compraService.guardarCompra(compra);
            modelAndView.addObject("compra", compra);
            modelAndView.addObject("producto", compra.getProducto());
        } catch (RuntimeException e) {
            modelAndView.setViewName("compra");
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
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
	@GetMapping("/modificarCompra/{dni}")
	public ModelAndView modificarCompra(@PathVariable String dni) {
	//el parametro del contructor del modelAndView es una vista HTML
		ModelAndView modelView = new ModelAndView("listaCompra");
		modelView.addObject("compraAModificar",compraService.consultarCompraDni(dni));
		modelView.addObject("band",false);
		  return modelView;
	
}
	
	@GetMapping("/recibo/{id}")
	public ModelAndView mostrarRecibo(@PathVariable String id) {
	    ModelAndView modelView = new ModelAndView("recibo");
	    try {
	        // Consulta la compra por el ID
	        Compra compra = compraService.consultarCompraIdCompra(id);
	        
	        // Verifica que la compra y el producto existan
	        if (compra != null && compra.getProducto() != null && compra.getCliente() != null) {
	            modelView.addObject("compra", compra);
	            modelView.addObject("producto", compra.getProducto()); // Datos del producto
	            modelView.addObject("cliente", compra.getCliente());   // Datos del cliente
	        } else {
	            modelView.addObject("error", "No se encontró la compra o los datos asociados para el ID: " + id);
	        }
	    } catch (Exception e) {
	        modelView.addObject("error", "Error al procesar la solicitud: " + e.getMessage());
	    }
	    return modelView;
	}
	
	
	 @GetMapping("/compra/{id}")
	    public ModelAndView mostrarCompra(@PathVariable String id) {
	        ModelAndView modelView = new ModelAndView("compra");

	        try {
	            // Obtén el producto correspondiente al ID
	            Producto producto = productoService.consultarProducto(id);

	            if (producto != null) {
	                modelView.addObject("producto", producto); // Producto relacionado
	                modelView.addObject("clientes", clienteService.listarTodosClientesActivos()); // Lista de clientes
	            } else {
	                modelView.addObject("error", "Producto no encontrado.");
	            }
	        } catch (RuntimeException e) {
	            modelView.addObject("error", e.getMessage());
	        }

	        return modelView;
	    }
	
	 @PostMapping("/recibo")
	 public ModelAndView procesarCompra(
	     @RequestParam String productoId,
	     @RequestParam String clienteId,
	     @RequestParam Integer cantidad) {
	     
	     ModelAndView modelView = new ModelAndView("recibo");

	     try {
	         // Busca el producto y el cliente
	         Producto producto = productoService.consultarProducto(productoId);
	         Cliente cliente = clienteService.consultarCliente(clienteId);

	         // Lógica de la compra (puedes guardarla en la base de datos si es necesario)
	         Compra compra = new Compra();
	         compra.setProducto(producto);
	         compra.setCliente(cliente);
	         compra.setCantidad(cantidad);
	         compra.setPrecioTotal(producto.getPrecio() * cantidad);
	         compra.setFormaPago("Tarjeta de Débito"); // Por ejemplo

	         // Guarda la compra en la base de datos
	         compraService.guardarCompra(compra);

	         // Pasa los datos a la vista de recibo
	         modelView.addObject("producto", producto);
	         modelView.addObject("cliente", cliente);
	         modelView.addObject("compra", compra);

	     } catch (Exception e) {
	         modelView.addObject("error", "Error al procesar la compra: " + e.getMessage());
	     }

	     return modelView;
	 }
	
	
}
