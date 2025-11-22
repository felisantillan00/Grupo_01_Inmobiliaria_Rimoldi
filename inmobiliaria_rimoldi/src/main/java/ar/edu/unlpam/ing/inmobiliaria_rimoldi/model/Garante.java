package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garante extends Persona{
    private double ingresos;
    private String empresa_trabaja;
    private String contacto_trabaja;
}
