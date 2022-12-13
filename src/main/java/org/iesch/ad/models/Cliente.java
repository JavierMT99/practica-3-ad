package org.iesch.ad.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente", catalog = "ventas")
public class Cliente {
    @Id
    private int id;
    private String nombre;
    private String direccion;
    private String poblacion;
    private String telef;

    @Column(unique = true)
    private String nif;
}
