package com.javierlobo.clientes.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javierlobo.clientes.persistence.entity.ClienteEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
