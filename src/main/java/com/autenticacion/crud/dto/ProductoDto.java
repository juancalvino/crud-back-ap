package com.autenticacion.crud.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductoDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private double precio;

    public ProductoDto(){}

    public ProductoDto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}
