package com.unibe.solid.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long id;
    private String cliente;
    private List<Producto> productos = new ArrayList<>();
}
