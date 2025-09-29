package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comercial")
@PrimaryKeyJoinColumn(name = "idPropiedad")
public class Comercial extends Propiedad {
    @Column(name = "permisos_municipales")
    private boolean permisosMunicipales;
    private boolean baño;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;

    public Comercial(boolean permisosMunicipales, boolean baño, boolean cocina, boolean vidriera, boolean deposito){
        this.permisosMunicipales = permisosMunicipales;
        this.baño = baño;
        this.cocina = cocina;
        this.vidriera = vidriera;
        this.deposito = deposito;
    }
}
