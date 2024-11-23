package ar.edu.ies6.tf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.ies6.tf.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository <Cliente,String> {

	//se creo un nuevo metodo
		List <Cliente> findByEstado (Boolean estado);
}