package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Estado;
import org.sql2o.*;

@Repository
public class EstadoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Estado findByNombre(String nombreEstado) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM estado WHERE nombreEstado = :nombreEstado";
            return con.createQuery(sql)
                      .addParameter("nombreEstado", nombreEstado)
                      .executeAndFetchFirst(Estado.class);
        }
    }
}
