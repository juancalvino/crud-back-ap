package com.autenticacion.crud.controller;

import com.autenticacion.crud.dto.Mensaje;
import com.autenticacion.crud.dto.ProductoDto;
import com.autenticacion.crud.model.Producto;
import com.autenticacion.crud.service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list() {
        return new ResponseEntity(productoService.list(), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("id") long idProducto) {
        if (!productoService.existProductoById(idProducto)) {
            return new ResponseEntity(new Mensaje("No Existe el producto"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productoService.getProducto(idProducto).get(), HttpStatus.OK);
    }

    @GetMapping("/detailsname/{nombre}")
    public ResponseEntity<Producto> getProductoByNombre(@PathVariable("nombre") String nombreProducto) {
        if (!productoService.existProductoByName(nombreProducto)) {
            return new ResponseEntity(new Mensaje("No Existe el producto"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(productoService.getProductoByNombre(nombreProducto).get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody ProductoDto productoDto) {

        if (StringUtils.isBlank(productoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.equals(null) || productoDto.getPrecio() < 0) {
            return new ResponseEntity(new Mensaje("El precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        }
        if (productoService.existProductoByName(productoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre del producto ya existe"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.saveProducto(producto);
        return new ResponseEntity(new Mensaje("El producto fue creado"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(
            @PathVariable("id") long idProducto,
            @RequestBody ProductoDto productoDto) {

        if (!productoService.existProductoById(idProducto)) {
            return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        if (productoService.existProductoByName(productoDto.getNombre())
                && productoService.getProductoByNombre(productoDto.getNombre()).get().getId() != idProducto) {
            return new ResponseEntity(new Mensaje("El producto ya existe con otro nombre"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(productoDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (productoDto.equals(null) || productoDto.getPrecio() < 0) {
            return new ResponseEntity(new Mensaje("El precio debe ser mayor a 0"), HttpStatus.BAD_REQUEST);
        }

        Producto producto = productoService.getProducto(idProducto).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.saveProducto(producto);
        return new ResponseEntity(new Mensaje("El producto fue actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long idProducto) {
        if (!productoService.existProductoById(idProducto)) {
            return new ResponseEntity(new Mensaje("El producto no existe"), HttpStatus.NOT_FOUND);
        }
        productoService.deleteProducto(idProducto);
        return new ResponseEntity(new Mensaje("El producto fue eliminado"), HttpStatus.OK);
    }

}
