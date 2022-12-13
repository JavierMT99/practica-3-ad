package org.iesch.ad.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "departamento", catalog = "personal")
public class Departamento {
    @Id
    private int id_dep;
    private String nombre;
    private String localidad;

}
