package ar.edu.ies6.tf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.ies6.tf.model.Compra;

public interface CompraRepository  extends CrudRepository<Compra,String>{
 
	//se creo un nuevo metodo
	List<Compra> findByEstado (Boolean estado);
}
