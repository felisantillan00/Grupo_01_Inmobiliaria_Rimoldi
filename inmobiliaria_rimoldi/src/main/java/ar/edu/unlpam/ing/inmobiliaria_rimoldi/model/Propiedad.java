package ar.edu.unlpam.ing.inmobiliaria_rimoldi.model;
// import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer idPropiedad;
    private String direccion;
    private Integer m2Cubiertos;
    private Integer m2Descubiertos;
    private String condicionesGarantes;
    private double expensas;
    private double gastos;
    // private Timestamp fechaPrecioMinimo;
    private String ciudad;
    private boolean enAlquiler;
    private boolean enVenta;
    private double valor_alquiler;
    private double valor_venta;
    private String descripcion;
    private String titulo;
    private double latitud;
    private double longitud;
    private Long dniPropietario;
}