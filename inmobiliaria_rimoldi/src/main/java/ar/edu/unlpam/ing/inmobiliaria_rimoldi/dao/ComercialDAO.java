package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Comercial;
import org.sql2o.*;
@Repository
public class ComercialDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();
    // Buscamos la propiedad    
    public Comercial findById(Integer idPropiedad) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT                     
                    idPropiedad,
                    permisos_municipales AS permisosMunicipales,
                    cocina,
                    vidriera,
                    deposito,
                    baño        
                FROM comercial WHERE idPropiedad = :idPropiedad
            """;
            return con.createQuery(sql)
                    .addParameter("idPropiedad", idPropiedad)
                    .executeAndFetchFirst(Comercial.class);
        }
    }
    
    // Guardamos la propiedad
    public Comercial save(Comercial comercial) {
        try (Connection con = sql2o.open()) {
            String sqlCom = """
                INSERT INTO comercial(
                    idPropiedad, permisos_municipales, baño, cocina, vidriera, deposito
                ) VALUES (
                    :idPropiedad, :permisosMunicipales, :baño, :cocina, :vidriera, :deposito
                )
            """;

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