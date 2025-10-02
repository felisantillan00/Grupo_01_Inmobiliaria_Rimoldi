package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "detallepago")
public class DetallePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle")
    private int idDetalle;

    @Column(name = "concepto")
    private String concepto;

    @Column(name = "monto")
    private double monto;

    private double intereses;
    private double montoFinal;

    // Un pago puede tener varios detalles de pago.
    @ManyToOne
    @JoinColumn(name = "idPago", referencedColumnName = "idPago")
    private Pago pago;
}