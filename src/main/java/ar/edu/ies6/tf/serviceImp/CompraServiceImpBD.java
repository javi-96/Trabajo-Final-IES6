package ar.edu.ies6.tf.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.ies6.tf.model.Compra;
import ar.edu.ies6.tf.repository.CompraRepository;
import ar.edu.ies6.tf.service.ICompraService;


@Service
@Qualifier("servicioCompraBD")
public class CompraServiceImpBD implements ICompraService {
	@Autowired
	CompraRepository compraRepository;
	@Override
	public void guardarCompra(Compra compra) {
		// TODO Auto-generated method stub
		compra.setEstado(true);
		compraRepository.save(compra);
	}

	@Override
	public void eliminarCompra(String dni) {
		// TODO Auto-generated method stub
		Optional<Compra> compraEncontrado =compraRepository.findById(dni);
		compraEncontrado.get().setEstado(false);
		if (compraEncontrado.isPresent()) {
            Compra compra = compraEncontrado.get();
            compra.setEstado(false); // Cambia el estado a falso
            compraRepository.save(compra);
        } else {
            throw new RuntimeException("Compra no encontrada para el DNI: " + dni);
        }
	}
		
	public void modificarCompra(Compra compraModificada) {
		// TODO Auto-generated method stub
		
	}

	 @Override
	    public Compra consultarCompraDni(String dni) {
	        return compraRepository.findById(dni)
	                .orElseThrow(() -> new RuntimeException("Compra no encontrada para el DNI: " + dni));
	    }
	 
	 @Override
	 public Compra consultarCompraIdCompra(String idCompra) {
	     return compraRepository.findByIdCompra(idCompra)
	         .orElseThrow(() -> new RuntimeException("Compra no encontrada para el ID: " + idCompra));
	 }
	 
	 
	@Override
	public List<Compra> listarTodasCompras() {
		// TODO Auto-generated method stub
		return (List<Compra>) compraRepository.findAll();
	}

	@Override
	public List<Compra> listarTodasComprasActivas() {
		// TODO Auto-generated method stub
		return (List<Compra>) compraRepository.findByEstado(true);
	}

	
}
