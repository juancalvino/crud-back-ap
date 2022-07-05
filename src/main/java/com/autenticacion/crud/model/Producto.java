package com.autenticacion.crud.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Double precio;

    public Producto(){}

    public Producto(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
