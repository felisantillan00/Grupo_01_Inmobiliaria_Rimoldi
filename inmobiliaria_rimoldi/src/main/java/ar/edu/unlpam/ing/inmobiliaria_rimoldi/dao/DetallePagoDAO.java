package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.DetallePago;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class DetallePagoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public DetallePago save(DetallePago detallePago) {
        try (Connection con = sql2o.open()) {
            String sql = """
                INSERT INTO detallepago(concepto, monto, idPago)
                VALUES(:concepto, :monto, :idPago)
            """;

            Object id = con.createQuery(sql, true)
                .addParameter("concepto", detallePago.getConcepto())
                .addParameter("monto", detallePago.getMonto())
                .addParameter("idPago", detallePago.getIdPago())
                .executeUpdate()
                .getKey();

            detallePago.setIdDetalle(((Number) id).intValue());
            return detallePago;
        }
    }

    public List<DetallePago> findByIdPago(int idPago) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT * FROM detallepago WHERE idPago = :idPago
            """;
            return con.createQuery(sql)
                    .addParameter("idPago", idPago)
                    .executeAndFetch(DetallePago.class);
        }
    }
}