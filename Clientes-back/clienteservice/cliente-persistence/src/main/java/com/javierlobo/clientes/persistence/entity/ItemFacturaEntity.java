package com.javierlobo.clientes.persistence.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="facturas_items")
public class ItemFacturaEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4874659585654226187L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private ProductoEntity producto;

	/**
	 * 
	 */
	public ItemFacturaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param cantidad
	 * @param producto
	 */
	public ItemFacturaEntity(Long id, Integer cantidad, ProductoEntity producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	
	public Double getImporte() {
		return cantidad.doubleValue()*producto.getPrecio();
	}
}
