package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propiedad")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"   
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Familiar.class, name = "Familiar"),
    @JsonSubTypes.Type(value = Comercial.class, name = "Comercial")
})
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPropiedad")
    private Integer idPropiedad;
    private String direccion;
    @Column(name = "m2_cubiertos")
    private Integer m2Cubiertos;
    @Column(name = "m2_descubiertos")
    private Integer m2Descubiertos;
    @Column(name = "condiciones_garantes")
    private String condicionesGarantes;
    private double expensas;
    private double gastos;
    @Column(name = "fecha_precio_minimo")
    private LocalDateTime fechaPrecioMinimo;
    private String ciudad;
    private boolean enAlquiler;
    private boolean enVenta;
    private double valor_alquiler;
    private double valor_venta;
    private String descripcion;
    private String titulo;
    private double latitud;
    private double longitud;

    @ManyToOne
    @JoinColumn(name = "dniPropietario", referencedColumnName = "dni", nullable = false)
    private Propietario propietario;
}