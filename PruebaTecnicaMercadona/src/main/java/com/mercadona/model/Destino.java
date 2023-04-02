package com.mercadona.model;


public class Destino {
	private Integer codigo;
	private String destino;
	private final TipoInstancia tipoInstancia = new TipoInstancia ("destino");
	
	
	
	public Destino() {
		
	}


	public Destino(Integer codigo, String destino) {
		this.codigo = codigo;
		this.destino = destino;
	}
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getTipoInstancia() {
		return tipoInstancia.getTipoInstancia();
	}
	
	
}
