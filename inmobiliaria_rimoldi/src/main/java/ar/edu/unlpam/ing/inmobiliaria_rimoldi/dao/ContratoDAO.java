package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Contrato;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class ContratoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o(); // tu singleton

    // Buscar por id
    public Contrato findById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM contrato WHERE nro_contrato = :id";
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Contrato.class);
        }
    }

    // Guardar
    public Contrato save(Contrato contrato) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO contrato(nro_contrato, fecha_inicio, fecha_fin, fecha_cancelacion, idPropiedad, dniPropietario) " +
                         "VALUES(:nroContrato, :fechaInicio, :fechaFin, :fechaCancelacion, :idPropiedad, :dniPropietario)";
            Object key = con.createQuery(sql, true)
                            .addParameter("nroContrato", contrato.getNroContrato())
                            .addParameter("fechaInicio", contrato.getFechaInicio())
                            .addParameter("fechaFin", contrato.getFechaFin())
                            .addParameter("fechaCancelacion", contrato.getFechaCancelacion())
                            .addParameter("idPropiedad", contrato.getPropiedad().getIdPropiedad())
                            .addParameter("dniPropietario", contrato.getPropietario().getDni())
                            .executeUpdate()
                            .getKey();
            contrato.setNroContrato(((Number) key).intValue());
            return contrato;
        }
    }
}