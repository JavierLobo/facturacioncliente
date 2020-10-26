package com.javierlobo.clientes.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="facturas")
public class FacturaEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1227122368661459940L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacio") 
	private String descripcion;
	
	@NotEmpty(message = "no puede estar vacio") 
	private String observacion;

	@NotNull(message="no puede estar vacio")
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@JsonIgnoreProperties({"facturas", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_id")
	private ClienteEntity cliente;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "facturas_id")
	private List<ItemFacturaEntity> items;
	
	/**
	 * Este método se ejecuta antes de guardar los datos, por lo que se usa   
	 * para asignar el valor a createAt como fecha de creacion de la factura
	 */
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	/**
	 * 
	 */
	public FacturaEntity() {
		items = new ArrayList<ItemFacturaEntity>();
	}

	/**
	 * @param id
	 * @param descripcion
	 * @param observacion
	 * @param createAt
	 * @param cliente Cliente al que se le emite la factura
	 * @param items Lista de items que contiene la factura, sus líneas
	 */
	public FacturaEntity(Long id, 
			@NotEmpty(message = "no puede estar vacio") String descripcion, 
			@NotEmpty(message = "no puede estar vacio") String observacion, 
			@NotNull(message = "No puede estar vacío") Date createAt,
			ClienteEntity cliente,
			List<ItemFacturaEntity> items) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.createAt = createAt;
		this.cliente = cliente;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public List<ItemFacturaEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemFacturaEntity> items) {
		this.items = items;
	}
	
	public Double getTotal() {
		Double total = 0.00;

		for(ItemFacturaEntity item: items) {
			total += item.getImporte();
		}
		return total;
	}
}
