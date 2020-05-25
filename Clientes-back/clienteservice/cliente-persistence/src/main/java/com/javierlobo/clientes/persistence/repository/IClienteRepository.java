package com.javierlobo.clientes.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.javierlobo.clientes.persistence.entity.ClienteEntity;

public interface IClienteRepository extends CrudRepository<ClienteEntity, Long> {

}
