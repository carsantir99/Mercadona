package com.mercadona.model;

public enum DestinoEnum {
	ENTRE_1_5("España"),
	SEIS("Portugal"),
	OCHO("Almacenes"),
	NUEVE("Oficinas Mercadona"),
	CERO("Colmenas");

	private final String nombre;
	DestinoEnum(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
        return nombre;
    }
}
