package com.javierlobo.clientes.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javierlobo.clientes.persistence.entity.ClienteEntity;
import com.javierlobo.clientes.persistence.entity.RegionEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
	
	@Query("from RegionEntity")
	public List<RegionEntity> findAllRegiones();
}
