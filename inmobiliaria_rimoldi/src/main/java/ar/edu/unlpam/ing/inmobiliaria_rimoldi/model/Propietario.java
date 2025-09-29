package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "propietario")
@PrimaryKeyJoinColumn(name = "dniPropietario", referencedColumnName = "dni")
public class Propietario extends Persona {
    @Column(name = "cbu", nullable = false)
    private Long cbu;
}
