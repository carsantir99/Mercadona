package com.mercadona.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "producto")
public class Producto {
	
	@Transient
	private final TipoInstancia tipoInstancia = new TipoInstancia ("producto");
	@Id
	private Integer codigo;
	private String nombre;
	
	
	
	public Producto() {
		
	}
	
	public Producto(Integer codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return tipoInstancia.getTipoInstancia();
	}
	
}
