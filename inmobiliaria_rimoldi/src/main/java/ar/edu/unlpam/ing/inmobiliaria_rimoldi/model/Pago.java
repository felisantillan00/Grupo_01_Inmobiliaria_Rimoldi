package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    private int idPago;
    private Date fechaPago;
    private double montoTotal;
    private int nroContrato; // solo el ID
    private List<DetallePago> detalles = new ArrayList<>();
}