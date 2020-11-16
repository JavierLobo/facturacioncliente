package com.javierlobo.clientes.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierlobo.clientes.logic.ICrudClienteService;
import com.javierlobo.clientes.persistence.entity.ClienteEntity;
import com.javierlobo.clientes.persistence.entity.FacturaEntity;
import com.javierlobo.clientes.persistence.entity.ProductoEntity;
import com.javierlobo.clientes.persistence.entity.RegionEntity;
import com.javierlobo.clientes.persistence.repository.IClienteRepository;
import com.javierlobo.clientes.persistence.repository.IFacturaRepository;
import com.javierlobo.clientes.persistence.repository.IProductoRepository;

@Service
public class CrudClienteServiceImpl implements ICrudClienteService {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IFacturaRepository facturaRepository;
		
	@Autowired
	private IProductoRepository productoRepository;
	
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
		return clienteRepository.findAllRegiones();
	}

	@Override
	@Transactional(readOnly = true)
	public FacturaEntity findFacturaById(Long id) {
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public FacturaEntity saveFactura(FacturaEntity factura) {
		return facturaRepository.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductoEntity> findProductoByNombre(String term) {
		return productoRepository.findByNombreContaining(term);
	}
	
}
