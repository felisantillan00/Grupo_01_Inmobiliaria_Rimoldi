package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propiedad;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PropiedadDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o(); 

    // Buscar por id
    public Propiedad findById(Integer id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM propiedad WHERE idPropiedad = :id";
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Propiedad.class);
        }
    }

    // Insertar
    public Propiedad save(Propiedad propiedad) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO propiedad(direccion, m2_cubiertos, m2_descubiertos, condiciones_garantes, expensas, gastos, " +
                         "fecha_precio_minimo, ciudad, enAlquiler, enVenta, valor_alquiler, valor_venta, descripcion, titulo, latitud, longitud, dniPropietario) " +
                         "VALUES(:direccion, :m2Cubiertos, :m2Descubiertos, :condicionesGarantes, :expensas, :gastos, " +
                         ":fechaPrecioMinimo, :ciudad, :enAlquiler, :enVenta, :valor_alquiler, :valor_venta, :descripcion, :titulo, :latitud, :longitud, :dniPropietario)";
            Object id = con.createQuery(sql, true) // `true` devuelve la key generada
                .addParameter("direccion", propiedad.getDireccion())
                .addParameter("m2Cubiertos", propiedad.getM2Cubiertos())
                .addParameter("m2Descubiertos", propiedad.getM2Descubiertos())
                .addParameter("condicionesGarantes", propiedad.getCondicionesGarantes())
                .addParameter("expensas", propiedad.getExpensas())
                .addParameter("gastos", propiedad.getGastos())
                .addParameter("fechaPrecioMinimo", propiedad.getFechaPrecioMinimo())
                .addParameter("ciudad", propiedad.getCiudad())
                .addParameter("enAlquiler", propiedad.isEnAlquiler())
                .addParameter("enVenta", propiedad.isEnVenta())
                .addParameter("valor_alquiler", propiedad.getValor_alquiler())
                .addParameter("valor_venta", propiedad.getValor_venta())
                .addParameter("descripcion", propiedad.getDescripcion())
                .addParameter("titulo", propiedad.getTitulo())
                .addParameter("latitud", propiedad.getLatitud())
                .addParameter("longitud", propiedad.getLongitud())
                .addParameter("dniPropietario", propiedad.getPropietario().getDni())
                .executeUpdate()
                .getKey();
                int idProp = ((Number) id).intValue();
                propiedad.setIdPropiedad(idProp);
            return propiedad;
        }
    }
}