package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contrato")
public class Contrato {
    @Id
    @Column(name = "nro_contrato")
    private int nroContrato;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "fecha_cancelacion")
    private Date fechaCancelacion;

    // Una propiedad puede tener varios contratos a lo largo del tiempo
    @ManyToOne
    @JoinColumn(name = "idPropiedad", referencedColumnName = "idPropiedad")
    private Propiedad propiedad;

    // Un propietario puede tener muchos contratos
    @ManyToOne
    @JoinColumn(name = "dniPropietario", referencedColumnName = "dniPropietario")
    private Propietario propietario;
}