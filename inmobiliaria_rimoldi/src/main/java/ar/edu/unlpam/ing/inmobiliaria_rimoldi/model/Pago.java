package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pago")
public class Pago {
    @Id
    @Column(name = "idPago")
    private int idPago;
    private Date fecha_pago;
    private double monto_total;
    private int nro_contrato;
}