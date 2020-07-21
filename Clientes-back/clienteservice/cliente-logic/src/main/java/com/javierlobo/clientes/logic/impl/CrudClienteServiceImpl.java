package com.javierlobo.clientes.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierlobo.clientes.logic.ICrudClienteService;
import com.javierlobo.clientes.persistence.entity.ClienteEntity;
import com.javierlobo.clientes.persistence.entity.RegionEntity;
import com.javierlobo.clientes.persistence.repository.IClienteRepository;

@Service
public class CrudClienteServiceImpl implements ICrudClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
		
	@Override
	@Transactional(readOnly = true)
	public List<ClienteEntity> findAll() {
		return (List<ClienteEntity>) clienteRepository.findAll();
	}
	
	@Override
	public Page<ClienteEntity> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteEntity findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public ClienteEntity save(ClienteEntity cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegionEntity> findAllRegiones() {
		// TODO Auto-generated method stub
		return clienteRepository.findAllRegiones();
	}
	
}
