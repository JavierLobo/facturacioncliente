package com.javierlobo.clientes.persistence.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity()
@Table(name="clientes")
public class ClienteEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(name = "nombre", nullable=false)
	private String nombre;

	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "apellido", nullable=false)
	private String apellido;

	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "no es una dirección de correo bien formada")
	@Column(name = "email", nullable=false, unique=true)
	private String email;
	
	@NotNull(message = "No puede estar vacío")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String foto;

	@NotNull(message = "La región no debe estar vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private RegionEntity region;
	
	@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "cliente", cascade=CascadeType.ALL)
	private List<FacturaEntity> facturas;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public ClienteEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param createAt
	 * @param foto
	 * @param region
	 * @param facturas
	 */
	public ClienteEntity(Long id,
			@NotEmpty(message = "no puede estar vacio") @Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12") String nombre,
			@NotEmpty(message = "no puede estar vacio") String apellido,
			@NotEmpty(message = "no puede estar vacio") @Email(message = "no es una dirección de correo bien formada") String email,
			@NotNull(message = "No puede estar vacío") Date createAt, String foto,
			@NotNull(message = "La región no debe estar vacía") RegionEntity region, List<FacturaEntity> facturas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
		this.foto = foto;
		this.region = region;
		this.facturas = facturas;
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
	
	public Date getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}

	public List<FacturaEntity> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaEntity> facturas) {
		this.facturas = facturas;
	}
}
