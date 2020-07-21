package com.javierlobo.clientes.logic;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javierlobo.clientes.persistence.entity.ClienteEntity;
import com.javierlobo.clientes.persistence.entity.RegionEntity;

public interface ICrudClienteService {

	public List<ClienteEntity> findAll(); 
	
	public Page<ClienteEntity> findAll(Pageable pageable); 

	public ClienteEntity findById(Long id);
	
	public ClienteEntity save(ClienteEntity cliente);
	
	public void delete(Long id);
	
	public List<RegionEntity> findAllRegiones();

}
