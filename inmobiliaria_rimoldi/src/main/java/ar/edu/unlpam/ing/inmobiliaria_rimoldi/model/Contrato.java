package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {
    private int nroContrato;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaCancelacion;
    private int idPropiedad; // FK a propiedad
    private Long dniPropietario; // FK a propietario
}