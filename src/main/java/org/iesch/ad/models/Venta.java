package org.iesch.ad.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "venta", catalog = "ventas")
public class Venta {
    @Id
    private int id_venta;
    private Date fecha;
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto id_producto;

    @Override
    public String toString() {
        return "Venta{" +
                "id_venta=" + id_venta +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", id_cliente=" + id_cliente.getId() +
                ", nombre_cliente=" + id_cliente.getNombre() +
                ", direccion_cliente=" + id_cliente.getDireccion() +
                ", poblacion_cliente=" + id_cliente.getPoblacion() +
                ", telefono_cliente=" + id_cliente.getTelef() +
                ", nif_cliente=" + id_cliente.getNif() +
                ", id_producto=" + id_producto.getId() +
                ", descripcion_producto=" + id_producto.getDescripcion() +
                ", stock_actual_producto=" + id_producto.getStockactual() +
                ", stock_minimo_producto=" + id_producto.getStockminimo() +
                ", precio_producto=" + id_producto.getPrecio() +
                '}';
    }

    public String toStringExtra() {
        return "Venta{" +
                "id_venta=" + id_venta +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", id_cliente=" + id_cliente.getId() +
                ", nombre_cliente=" + id_cliente.getNombre() +
                ", direccion_cliente=" + id_cliente.getDireccion() +
                ", poblacion_cliente=" + id_cliente.getPoblacion() +
                ", telefono_cliente=" + id_cliente.getTelef() +
                ", nif_cliente=" + id_cliente.getNif() +
                ", id_producto=" + id_producto.getId() +
                ", descripcion_producto=" + id_producto.getDescripcion() +
                ", stock_actual_producto=" + id_producto.getStockactual() +
                ", stock_minimo_producto=" + id_producto.getStockminimo() +
                ", precio_producto=" + id_producto.getPrecio() +
                "}\n";
    }
}
