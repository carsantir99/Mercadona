package com.mercadona.model;

public class EAN {
	private Producto producto;
	private Proveedor proveedor;
	private Destino destino;
	
	public EAN() {
		
	}

	public EAN(Producto producto, Proveedor proveedor, Destino destino) {
	
		this.producto = producto;
		this.proveedor = proveedor;
		this.destino = destino;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	
	
	
}
