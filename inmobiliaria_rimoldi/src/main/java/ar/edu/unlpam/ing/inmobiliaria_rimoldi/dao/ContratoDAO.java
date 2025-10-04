package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Contrato;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;

@Repository
public class ContratoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Contrato findById(int nroContrato) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM contrato WHERE nro_contrato = :id";

            org.sql2o.data.Table table = con.createQuery(sql)
                            .addParameter("id", nroContrato)
                            .executeAndFetchTable();

            if (table.rows().isEmpty()) return null;

            Row row = table.rows().get(0);
            Contrato contrato = new Contrato();
            contrato.setNroContrato((int) row.getInteger("nro_contrato"));
            contrato.setFechaInicio(new java.util.Date(row.getDate("fecha_inicio").getTime()));
            contrato.setFechaFin(row.getDate("fecha_fin") != null 
                ? new java.util.Date(row.getDate("fecha_fin").getTime())
                : null);
            contrato.setFechaCancelacion(row.getDate("fecha_cancelacion") != null 
                ? new java.util.Date(row.getDate("fecha_cancelacion").getTime())
                : null);
            return contrato;
        }
    }
    // Guardar
    public Contrato save(Contrato contrato) {
        try (Connection con = sql2o.open()) {
            String sql = """
                INSERT INTO contrato(
                    nro_contrato, fecha_inicio, fecha_fin, fecha_cancelacion, idPropiedad, dniPropietario
                ) VALUES (
                    :nroContrato, :fechaInicio, :fechaFin, :fechaCancelacion, :idPropiedad, :dniPropietario
                )
            """;

            Object key = con.createQuery(sql, true)
                            .addParameter("nroContrato", contrato.getNroContrato())
                            .addParameter("fechaInicio", contrato.getFechaInicio())
                            .addParameter("fechaFin", contrato.getFechaFin())
                            .addParameter("fechaCancelacion", contrato.getFechaCancelacion())
                            .addParameter("idPropiedad", contrato.getIdPropiedad())
                            .addParameter("dniPropietario", contrato.getDniPropietario())
                            .executeUpdate()
                            .getKey();
            contrato.setNroContrato(((Number) key).intValue());
            return contrato;
        }
    }
}