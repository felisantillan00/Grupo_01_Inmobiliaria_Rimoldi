package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoContrato{
    private int nroContrato;
    private int idEstado;
    private Date fechaEstado;
}