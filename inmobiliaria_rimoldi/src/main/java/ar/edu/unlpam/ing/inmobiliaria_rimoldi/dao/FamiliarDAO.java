package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Familiar;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class FamiliarDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Familiar save(Familiar familiar) {
        try (Connection con = sql2o.open()) {
            String sqlFam = "INSERT INTO familiar(idPropiedad, cant_ambientes, cant_baños, cant_cocheras, piscina, permiteMascota, permiteNiños) " +
                            "VALUES(:idPropiedad, :cantAmbientes, :cantBaños, :cantCocheras, :piscina, :permiteMascota, :permiteNiños)";
            con.createQuery(sqlFam)
               .addParameter("idPropiedad", familiar.getIdPropiedad())
               .addParameter("cantAmbientes", familiar.getCantAmbientes())
               .addParameter("cantBaños", familiar.getCantBaños())
               .addParameter("cantCocheras", familiar.getCantCocheras())
               .addParameter("piscina", familiar.isPiscina())
               .addParameter("permiteMascota", familiar.isPermiteMascota())
               .addParameter("permiteNiños", familiar.isPermiteNinos())
               .executeUpdate();
            return familiar;
        }
    }
}