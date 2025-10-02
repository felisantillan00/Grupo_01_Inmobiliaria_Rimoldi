package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.Entity;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comercial")
@PrimaryKeyJoinColumn(name = "idPropiedad")
public class Comercial extends Propiedad {
    @Column(name = "permisos_municipales")
    private boolean permisosMunicipales;
    private boolean baño;
    private boolean cocina;
    private boolean vidriera;
    private boolean deposito;
}