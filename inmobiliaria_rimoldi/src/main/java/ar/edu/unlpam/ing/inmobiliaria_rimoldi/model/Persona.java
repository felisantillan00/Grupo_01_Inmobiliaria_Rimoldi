package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.time.LocalDate;
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
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @Column(name = "dni")
    private Long dni;

    private String nombre;
    private String email;
    private String celular;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    private String username;
    private Long cuil;
    private String password;
    private String direccion;
}