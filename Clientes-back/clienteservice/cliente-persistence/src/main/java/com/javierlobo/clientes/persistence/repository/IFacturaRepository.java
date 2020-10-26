package com.javierlobo.clientes.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.javierlobo.clientes.persistence.entity.FacturaEntity;

public interface IFacturaRepository extends CrudRepository<FacturaEntity, Long>{

	
}
