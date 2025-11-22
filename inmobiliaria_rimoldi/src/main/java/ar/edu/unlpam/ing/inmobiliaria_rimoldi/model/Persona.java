package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private Long dni;
    private String nombre;
    private String email;
    private String celular;
    private LocalDate fechaNac;
    private String username;
    private Long cuil;
    private String password;
    private String direccion;
}