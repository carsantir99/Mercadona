package com.mercadona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadona.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	  @Query("SELECT u FROM Producto u WHERE u.codigo = :codigo")
	  Producto getProducto(@Param("codigo") Integer codigo);
}
