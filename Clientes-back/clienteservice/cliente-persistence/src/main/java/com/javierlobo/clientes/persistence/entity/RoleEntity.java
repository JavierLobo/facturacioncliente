package com.javierlobo.clientes.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2949096703534024716L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String nombre;

	/**
	 * @param id
	 * @param username
	 */
	public RoleEntity(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
