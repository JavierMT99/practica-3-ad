package org.iesch.ad.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empleado", catalog = "personal")
public class Empleado {
    @Id
    private int id_emp;

    private String apellido;
    private String oficio;
    private Date fecha_alta;
    private Float salario;
    private Float comision;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_dep")
    private Departamento id_dep;

    @Override
    public String toString() {
        return "Empleado{" +
                "id_emp=" + id_emp +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", fecha_alta=" + fecha_alta +
                ", salario=" + salario +
                ", comision=" + comision +
                ", id_dep=" + id_dep.getId_dep() +
                '}';
    }

    public String toStringEmpleadoConDepartamento() {
        return "Empleado{" +
                "id_emp=" + id_emp +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", fecha_alta=" + fecha_alta +
                ", salario=" + salario +
                ", comision=" + comision +
                ", id_dep=" + id_dep.getId_dep() +
                ", nombre_departamento=" + id_dep.getNombre() +
                ", localidad_departamento=" + id_dep.getLocalidad() +
                '}';
    }
}
