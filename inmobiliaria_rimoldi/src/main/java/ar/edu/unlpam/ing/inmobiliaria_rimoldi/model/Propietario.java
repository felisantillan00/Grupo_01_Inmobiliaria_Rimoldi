package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propietario")
@PrimaryKeyJoinColumn(name = "dniPropietario")
public class Propietario extends Persona {
    @Column(name = "cbu", nullable = false)
    private Long cbu;
}