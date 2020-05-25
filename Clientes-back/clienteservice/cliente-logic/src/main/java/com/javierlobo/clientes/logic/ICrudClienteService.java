package com.javierlobo.clientes.logic;

import java.util.List;

import com.javierlobo.clientes.persistence.entity.ClienteEntity;

public interface ICrudClienteService {

	public List<ClienteEntity> findAll(); 

	public ClienteEntity findById(Long id);
	
	public ClienteEntity save(ClienteEntity cliente);
	
	public void delete(Long id);
	
}
