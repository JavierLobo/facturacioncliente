package com.javierlobo.clientes.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javierlobo.clientes.logic.ICrudClienteService;
import com.javierlobo.clientes.persistence.entity.FacturaEntity;
import com.javierlobo.clientes.persistence.entity.ProductoEntity;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}) // , methods= {RequestMethod.GET} // RequestMethod.POST
@RequestMapping("/api")
public class FacturaResource {

	@Autowired
	private ICrudClienteService clienteService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public FacturaEntity show(@PathVariable Long id) {
		return clienteService.findFacturaById(id);
	}

	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductoEntity> filtrarProductos(@PathVariable String term) {
		return clienteService.findProductoByNombre(term);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public FacturaEntity crear(@RequestBody FacturaEntity factura) {
		return clienteService.saveFactura(factura);
	}
}
