package com.javierlobo.clientes.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javierlobo.clientes.logic.ICrudClienteService;
import com.javierlobo.clientes.persistence.entity.ClienteEntity;

@RestController
@RequestMapping("/api")
public class CrudClientesResource {

	@Autowired
	private ICrudClienteService crudClienteService;
	
	@GetMapping("/clientes")
	public List<ClienteEntity> index() {
		return crudClienteService.findAll();	
	}	
	
	@GetMapping("/clientes/{id}")
	public ClienteEntity show(@PathVariable Long id) {
		return crudClienteService.findById(id);	
	}
	
	@PostMapping("/clientes") // POST PARA CREAR
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEntity create(@RequestBody ClienteEntity cliente) {
		return crudClienteService.save(cliente);	
	}

	@PutMapping("/clientes/{id}") // PUT PARA ACTUALIZAR
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEntity update(@RequestBody ClienteEntity cliente, @PathVariable Long id) {
		ClienteEntity clienteActual = crudClienteService.findById(id);
		
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());
		
		return crudClienteService.save(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		crudClienteService.delete(id);	
	}
}
