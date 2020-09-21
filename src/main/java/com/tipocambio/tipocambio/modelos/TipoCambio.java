package com.tipocambio.tipocambio.modelos;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TipoCambio {
    private Date fecha;
    private Double precioVenta;
    private Double precioCompra;
    private Double precioPromedio;
}
