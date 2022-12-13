package org.iesch.ad.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "producto", catalog = "ventas")
public class Producto {
    @Id
    private int id;
    private String descripcion;
    private int stockactual;
    private int stockminimo;
    private Float precio;

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", stockactual=" + stockactual +
                ", stockminimo=" + stockminimo +
                ", precio=" + precio +
                "}\n";
    }
}
