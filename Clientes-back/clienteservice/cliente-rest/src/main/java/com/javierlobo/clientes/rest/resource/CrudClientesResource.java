package com.javierlobo.clientes.rest.resource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javierlobo.clientes.logic.ICrudClienteService;
import com.javierlobo.clientes.persistence.entity.ClienteEntity;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}) // , methods= {RequestMethod.GET} // RequestMethod.POSt
@RequestMapping("/api")
public class CrudClientesResource {

	// ToDo: El log no va, hay que averiguar porqué.
	// private final Logger log = (Logger) LoggerFactory.logger(CrudClientesResource.class);
	
	@Autowired
	private ICrudClienteService crudClienteService;

	@GetMapping("/clientes")
	public List<ClienteEntity> index() {
		return crudClienteService.findAll();	
	}

	@GetMapping("/clientes/page/{page}")
	public Page<ClienteEntity> index(@PathVariable Integer page) {
		return crudClienteService.findAll(PageRequest.of(page, 4));
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		ClienteEntity cliente;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = crudClienteService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos.");
			response.put("exception", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.OK);		
	}
	
	@PostMapping("/clientes") // POST PARA CREAR
	public ResponseEntity<?> create(@Valid @RequestBody ClienteEntity cliente, BindingResult result) {
		ClienteEntity clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			return new ResponseEntity<Map<String, Object>>(validateRequestBody(result), HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = crudClienteService.save(cliente); 
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos.");
			response.put("exception", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El cliente ha sido creado con éxito.");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}") // PUT PARA ACTUALIZAR
	public ResponseEntity<?> update(@Valid @RequestBody ClienteEntity cliente, BindingResult result, @PathVariable Long id) {
		ClienteEntity clienteActual = crudClienteService.findById(id);
		ClienteEntity clienteUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			return new ResponseEntity<Map<String, Object>>(validateRequestBody(result), HttpStatus.BAD_REQUEST);
		}
		
		if(clienteActual==null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND); 	
		}
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteUpdate = crudClienteService.save(clienteActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos.");
			response.put("exception", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito.");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			
			// Eliminamos la imagen anterior antes de guardar
//			eliminarImagen(id);
			crudClienteService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos.");
			response.put("exception", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		response.put("mensaje", "El cliente ha sido eliminado con éxito.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		
		Map<String, Object> response = new HashMap<>();
		ClienteEntity cliente = crudClienteService.findById(id);
		
		if (!archivo.isEmpty()) {
			String nombreArchivo =UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
//			log.info(rutaArchivo.toString());
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
				
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
				response.put("exception", e.getMessage().concat(":").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			// Eliminamos la imagen anterior antes de guardar
			eliminarImagen(id);

			// Guardamos la imagen junto el objeto
			cliente.setFoto(nombreArchivo);
			crudClienteService.save(cliente);

			response.put("cliente", cliente);
			response.put("mensaje", "Se ha subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
//		log.info(rutaArchivo.toString());

		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("notUser64x64.png").toAbsolutePath();
			
			try {
				recurso = new UrlResource(rutaArchivo.toUri());
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			// log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.CREATED);
	}
	
	// ---------------------------------------------
	// METODOS PRIVADOS
	// ---------------------------------------------
	
	
	/**
	 * Elimina una imagen de asociada a un registro
	 * 
	 * @param id Identificador del registro
	 */
	private void eliminarImagen(Long id) {
		ClienteEntity cliente = crudClienteService.findById(id);
		
		String nombreFotoAnterior = cliente.getFoto();
		
		if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
			Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
			File archivoFotoAnterior = rutaFotoAnterior.toFile();
			
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
				
			}
		} 
	}
	
	
	private Map<String, Object> validateRequestBody(BindingResult result) {
		Map<String, Object> response = new HashMap<>();

//		Solucion A
//		----------
//		List<String> errors = new ArrayList<>();
//		for(FieldError err: result.getFieldErrors()) {
//			errors.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
//		}

//		Solucion B
//		----------
		List<String> errors = result.getFieldErrors()
				.stream()
				.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
				.collect(Collectors.toList());
			
		response.put("errors", errors);

		return response;
	}
	
	
}
