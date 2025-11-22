package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comercial extends Propiedad{
    private boolean permisosMunicipales;
    private boolean baño;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;
}