package com.javierlobo.clientes.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1143269547764629668L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	private Boolean enabled;

	private String nombre;
	
	private String apellido;
	
	@Column(unique = true)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", 
		joinColumns = @JoinColumn(name = "UsuarioEntity_id"), inverseJoinColumns = @JoinColumn(name = "RoleEntity_id"), 
		uniqueConstraints = {@UniqueConstraint(columnNames = {"UsuarioEntity_id", "RoleEntity_id"})})
	private List<RoleEntity> roles;

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param enabled
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param roles
	 */
	public UsuarioEntity(Long id, String username, String password, Boolean enabled, String nombre, String apellido,
			String email, List<RoleEntity> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.roles = roles;
	}

	public UsuarioEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
