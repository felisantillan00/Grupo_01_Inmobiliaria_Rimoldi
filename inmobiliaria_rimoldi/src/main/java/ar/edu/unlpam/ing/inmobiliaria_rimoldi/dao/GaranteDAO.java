package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Garante;
import org.springframework.stereotype.Repository;
import java.util.Map;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Table;


@Repository
public class GaranteDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();
    
    public Garante findById(Long dniGarante) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT 
                    p.dni,
                    p.nombre,
                    p.email,
                    p.celular,
                    p.fecha_nac as fechaNac,
                    p.cuil,
                    p.direccion,
                    g.ingresos,
                    g.empresa_trabaja,
                    g.contacto_trabaja
                FROM garante g
                JOIN persona p ON g.dniGarante = p.dni
                WHERE g.dniGarante = :dniGarante
            """;
            Table table = con.createQuery(sql)
                            .addParameter("dniGarante", dniGarante)
                            .executeAndFetchTable();
            if (table.rows().isEmpty()) return null;

            Map<String, Object> row = table.rows().get(0).asMap();
            Garante garante = new Garante();

            garante.setDni((Long) row.get("dni"));
            garante.setNombre((String) row.get("nombre"));
            garante.setEmail((String) row.get("email"));
            garante.setCelular((String) row.get("celular"));
            garante.setFechaNac(row.get("fecha_nac") != null ? ((java.time.LocalDateTime)row.get("fecha_nac")).toLocalDate() : null);
            garante.setCuil(row.get("cuil") != null ? ((Number) row.get("cuil")).longValue() : null);
            garante.setDireccion((String) row.get("direccion"));
            garante.setIngresos(row.get("ingresos") != null ? ((Number) row.get("ingresos")).doubleValue() : 0);
            garante.setEmpresa_trabaja((String) row.get("empresa_trabaja"));
            garante.setContacto_trabaja((String) row.get("contacto_trabaja"));            
            return garante;
        }
    }
}
