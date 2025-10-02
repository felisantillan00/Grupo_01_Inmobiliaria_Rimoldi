package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.DetallePago;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class DetallePagoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o(); 

    // Guardar
    public DetallePago save(DetallePago detallePago) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO detallepago(concepto, monto, intereses, montoFinal, idPago) " +
                         "VALUES(:concepto, :monto, :intereses, :montoFinal, :idPago)";
            Object id = con.createQuery(sql, true) // `true` para obtener la key generada
                .addParameter("concepto", detallePago.getConcepto())
                .addParameter("monto", detallePago.getMonto())
                .addParameter("intereses", detallePago.getIntereses())
                .addParameter("montoFinal", detallePago.getMontoFinal())
                .addParameter("idPago", detallePago.getPago().getIdPago()) // FK
                .executeUpdate()
                .getKey();
            int idDetalle = (((Number) id).intValue());
            detallePago.setIdDetalle(idDetalle);

            return detallePago;
        }
    }
}