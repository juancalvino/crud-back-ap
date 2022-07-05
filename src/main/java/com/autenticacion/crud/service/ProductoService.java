package com.autenticacion.crud.service;

import com.autenticacion.crud.model.Producto;

import java.util.List;
import java.util.Optional;


public interface ProductoService {

    Producto saveProducto(Producto producto);

    Optional<Producto> getProducto(long idProducto);

    Optional<Producto> getProductoByNombre(String nombreProducto);

    boolean existProductoById(long id);

    boolean existProductoByName(String nombreProducto);

    void deleteProducto(long idProducto);

    List<Producto> list();

}
