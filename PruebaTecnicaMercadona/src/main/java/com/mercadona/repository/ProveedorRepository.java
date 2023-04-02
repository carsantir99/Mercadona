package com.mercadona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercadona.model.Proveedor;



public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
	  @Query("SELECT u FROM Proveedor u WHERE u.codigo = :codigo")
	  Proveedor getProveedor(@Param("codigo") Integer codigo);
}
