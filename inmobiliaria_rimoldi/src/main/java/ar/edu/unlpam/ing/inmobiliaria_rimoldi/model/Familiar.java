package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "familiar")
@PrimaryKeyJoinColumn(name = "idPropiedad")
public class Familiar extends Propiedad {
    @Column(name = "cant_ambientes")
    private int cantAmbientes;
    @Column(name = "cant_baños")
    private int cantBaños;
    @Column(name = "cant_cocheras")
    private int cantCocheras;
    private boolean piscina;
    @Column(name = "permiteMascota")
    private boolean permiteMascota;
    @Column(name = "permiteNiños")
    private boolean permiteNinos;
}