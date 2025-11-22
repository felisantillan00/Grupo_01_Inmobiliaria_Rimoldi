package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inquilino extends Persona{
    private boolean mascotas;
    private String empresa_trabaja;
    private String cantidad_integrantes;
    private double ingresos;
    private boolean tieneRecargo;
    private double valorDeDeuda;
}