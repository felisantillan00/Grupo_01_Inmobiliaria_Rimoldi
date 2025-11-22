package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePago {
    private int idDetalle;
    private String concepto;
    private double monto;
    private int idPago;
}