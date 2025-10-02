package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Pago;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PagoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o(); // tu singleton

    // Guardar
    public Pago save(Pago pago) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO pago(fecha_pago, monto_total, nro_contrato) " +
                         "VALUES(:fechaPago, :montoTotal, :nroContrato)";
            Object id = con.createQuery(sql, true)
                .addParameter("fechaPago", pago.getFechaPago())
                .addParameter("montoTotal", pago.getMontoTotal())
                .addParameter("nroContrato", pago.getContrato().getNroContrato()) // FK
                .executeUpdate()
                .getKey();
            int idPago = (((Number) id).intValue());
            pago.setIdPago(idPago);

            return pago;
        }
    }
}