package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Familiar extends Propiedad {
    private int cantAmbientes;
    private boolean piscina;
    private boolean permiteMascota;
    private boolean permiteNiños;
    private int cantBaños;
    private int cantCocheras;
}