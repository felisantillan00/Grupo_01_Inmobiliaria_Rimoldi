package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propietario extends Persona {
    private Long cbu;
}
