package com.mercadona.model;



import com.mercadona.exceptions.EmptyCodeException;
import com.mercadona.exceptions.EmptyNameException;
import com.mercadona.exceptions.IncorrectFormatCodeException;

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
	
	public Producto(Integer codigo, String nombre) throws IncorrectFormatCodeException,EmptyCodeException, EmptyNameException {
		if(codigo!=null) {
		if(((int) Math.log10(codigo) + 1)==5){
			this.codigo = codigo;
		}else {
			throw new IncorrectFormatCodeException();
		}
		}else {
			throw new EmptyCodeException();
		}
		
		if(nombre==null || nombre.equals("")) {
			throw new EmptyNameException();
		}else {
			this.nombre = nombre;
		}
		
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
