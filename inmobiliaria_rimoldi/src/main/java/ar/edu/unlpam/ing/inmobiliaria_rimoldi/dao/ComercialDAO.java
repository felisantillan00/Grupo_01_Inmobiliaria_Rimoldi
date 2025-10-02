package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Comercial;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class ComercialDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Comercial save(Comercial comercial) {
        try (Connection con = sql2o.open()) {
            // 1. Insertar en comercial
            String sqlCom = "INSERT INTO comercial(idPropiedad, permisos_municipales, baño, cocina, vidriera, deposito) " +
                            "VALUES(:idPropiedad, :permisosMunicipales, :baño, :cocina, :vidriera, :deposito)";
            con.createQuery(sqlCom)
            .addParameter("idPropiedad", comercial.getIdPropiedad())
            .addParameter("permisosMunicipales", comercial.isPermisosMunicipales())
            .addParameter("baño", comercial.isBaño())
            .addParameter("cocina", comercial.isCocina())
            .addParameter("vidriera", comercial.isVidriera())
            .addParameter("deposito", comercial.isDeposito())
            .executeUpdate();

            return comercial;
        }
    }
}