package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED) // Permite herencia
public class Persona {
    @Id
    @Column(name = "dni")
    private Long dni;

    private String nombre;
    private String email;
    private String celular;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nac")
    private Date fechaNac;

    private String username;
    private Long cuil;
    private String password;
    private String direccion;
}