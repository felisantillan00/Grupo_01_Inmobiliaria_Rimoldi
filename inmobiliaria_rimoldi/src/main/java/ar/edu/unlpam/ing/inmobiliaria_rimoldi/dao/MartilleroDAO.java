package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Martillero;
import java.util.Map;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.data.Table;

@Repository
public class MartilleroDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();
    
    public Martillero findById(Long dniMartillero) {
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
                    m.nro_matricula AS nroMatricula
                FROM martillero m
                JOIN persona p ON m.dniMartillero = p.dni
                WHERE m.dniMartillero = :dniMartillero
            """;
            Table table = con.createQuery(sql)
                            .addParameter("dniMartillero", dniMartillero)
                            .executeAndFetchTable();
            if (table.rows().isEmpty()) return null;

            Map<String, Object> row = table.rows().get(0).asMap();
            Martillero martillero = new Martillero();

            martillero.setDni((Long) row.get("dni"));
            martillero.setNombre((String) row.get("nombre"));
            martillero.setEmail((String) row.get("email"));
            martillero.setCelular((String) row.get("celular"));
            martillero.setFechaNac(row.get("fecha_nac") != null ? ((java.time.LocalDateTime)row.get("fecha_nac")).toLocalDate() : null);
            martillero.setCuil(row.get("cuil") != null ? ((Number) row.get("cuil")).longValue() : null);
            martillero.setDireccion((String) row.get("direccion"));
            martillero.setNroMatricula(row.get("nroMatricula") != null ? ((Number) row.get("nroMatricula")).intValue() : 0);
            return martillero;
        }
    }
}