package ar.edu.ies6.tf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.ies6.tf.model.Cliente;

public interface ClienteRepository extends CrudRepository <Cliente,String> {

	//se creo un nuevo metodo
		List <Cliente> findByEstado (Boolean estado);
}