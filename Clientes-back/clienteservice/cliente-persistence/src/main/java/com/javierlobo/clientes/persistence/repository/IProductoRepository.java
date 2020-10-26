package com.javierlobo.clientes.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javierlobo.clientes.persistence.entity.ProductoEntity;

public interface IProductoRepository extends CrudRepository<ProductoEntity, Long>{
	
	@Query("select p from ProductoEntity p where p.nombre like %?1%")
	public List<ProductoEntity> findByNombre(String term);

	public List<ProductoEntity> findByNombreContaining(String term);
	
	public List<ProductoEntity> findByNombreStartingWithIgnoreCase(String term);
}
