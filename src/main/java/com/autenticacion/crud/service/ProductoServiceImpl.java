package com.autenticacion.crud.service;

import com.autenticacion.crud.model.Producto;
import com.autenticacion.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> getProducto(long idProducto) {
        return productoRepository.findById(idProducto);
    }

    @Override
    public Optional<Producto> getProductoByNombre(String nombreProducto) {
        return productoRepository.findByNombre(nombreProducto);
    }

    @Override
    public boolean existProductoById(long idProducto) {
        return productoRepository.existsById(idProducto);
    }

    @Override
    public boolean existProductoByName(String nombreProducto) {
        return productoRepository.existsByNombre(nombreProducto);
    }

    @Override
    public void deleteProducto(long idProducto) {
         productoRepository.deleteById(idProducto);
    }

    @Override
    public List<Producto> list() {
        return productoRepository.findAll();
    }
}
