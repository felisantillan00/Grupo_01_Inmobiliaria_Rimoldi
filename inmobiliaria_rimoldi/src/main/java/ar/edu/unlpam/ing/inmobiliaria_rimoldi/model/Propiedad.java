package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "propiedad")
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_precio_minimo")
    private Date fechaPrecioMinimo;
    private String ciudad;
    private boolean enAlquiler;
    private boolean enVenta;
    private double valor_alquiler;
    private double valor_venta;
    private String descripcion;
    private String titulo;
    private double latitud;
    private double longitud;

    // Relación con Propietario
    @ManyToOne
    @JoinColumn(name = "dniPropietario", referencedColumnName = "dni", nullable = false)
    private Propietario propietario;
        
    public Propiedad(String direccion, Integer m2_cubiertos, Integer m2_descubiertos, String condiciones_garantes, Double expensas, Double gastos, 
                    Date FechaPrecioMinimo, String ciudad, Boolean enAlquiler, Boolean enVenta, double valor_alquiler, double valor_venta,
                    String descripcion, String titulo, double latitud, double longitud, Propietario propietario) {
        this.direccion = direccion;
        this.m2Cubiertos = m2_cubiertos;
        this.m2Descubiertos = m2_descubiertos;
        this.condicionesGarantes = condiciones_garantes;
        this.expensas = expensas;
        this.gastos = gastos;
        this.fechaPrecioMinimo = FechaPrecioMinimo;
        this.ciudad = ciudad;
        this.enAlquiler = enAlquiler;
        this.enVenta = enVenta;
        this.valor_alquiler = valor_alquiler;
        this.valor_venta = valor_venta;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.propietario = propietario;
    }
}