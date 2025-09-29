package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
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

    public Familiar(int cantAmbientes, int cantBaños, int cantCocheras, boolean piscina, boolean permiteMascota, boolean permiteNinos) {
        this.cantAmbientes = cantAmbientes;
        this.cantBaños = cantBaños;
        this.cantCocheras = cantCocheras;
        this.piscina = piscina;
        this.permiteMascota = permiteMascota;
        this.permiteNinos = permiteNinos;
    }
}
