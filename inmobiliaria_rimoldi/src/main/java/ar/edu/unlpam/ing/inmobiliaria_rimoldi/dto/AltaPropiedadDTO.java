package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AltaPropiedadDTO {
    // Datos comunes
    private String tipoPropiedad;   // "comercial" o "familiar"
    private String tipoEstado;      // "enVenta" o "enAlquiler"
    private Long dniPropietario;

    private String direccion;
    private Integer m2Cubiertos;
    private Integer m2Descubiertos;
    private String condicionesGarantes;
    private Double expensas;
    private Double gastos;
    private String ciudad;
    private Boolean enAlquiler;
    private Boolean enVenta;
    private Double valor_alquiler;
    private Double valor_venta;
    private String descripcion;
    private String titulo;
    private Integer latitud;
    private Integer longitud;

    // Atributos solo para COMERCIAL
    private Boolean permisosMunicipales;
    private Boolean banio;
    private Boolean cocina;
    private Boolean vidriera;
    private Boolean deposito;

    // Atributos solo para FAMILIAR
    private Integer cantAmbientes;
    private Integer cantBanios;
    private Integer cantCocheras;
    private Boolean piscina;
    private Boolean permiteMascota;
    private Boolean permiteNinos;
}