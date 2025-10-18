package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import java.util.Map;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propietario;
import org.sql2o.data.Table;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

@Repository
public class PropietarioDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Propietario findById(Long dni) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT 
                    p.dni, p.nombre, p.email, p.celular, 
                    p.fecha_nac as fechaNac, 
                    p.cuil, p.direccion,
                    pr.cbu
                FROM persona p
                JOIN propietario pr ON p.dni = pr.dniPropietario
                WHERE p.dni = :dni
            """;

            Table table = con.createQuery(sql)
                            .addParameter("dni", dni)
                            .executeAndFetchTable();

            if (table.rows().isEmpty()) return null;

            Map<String, Object> row = table.rows().get(0).asMap();
            Propietario prop = new Propietario();

            prop.setDni((Long) row.get("dni"));
            prop.setNombre((String) row.get("nombre"));
            prop.setEmail((String) row.get("email"));
            prop.setCelular((String) row.get("celular"));
            prop.setFechaNac(row.get("fecha_nac") != null ? ((java.time.LocalDateTime)row.get("fecha_nac")).toLocalDate() : null);
            prop.setUsername((String) row.get("username"));
            prop.setCuil(row.get("cuil") != null ? ((Number) row.get("cuil")).longValue() : null);
            prop.setPassword((String) row.get("password"));
            prop.setDireccion((String) row.get("direccion"));
            prop.setCbu(row.get("cbu") != null ? ((Number) row.get("cbu")).longValue() : null);
            return prop;
        }
    }

    public Propietario save(Propietario propietario) {
        try (Connection con = sql2o.beginTransaction()) {
            String sqlPersona = """
                INSERT INTO persona(dni, nombre, email, celular, fecha_nac, username, cuil, password, direccion)
                VALUES(:dni, :nombre, :email, :celular, :fechaNac, :username, :cuil, :password, :direccion)
            """;

            con.createQuery(sqlPersona)
               .addParameter("dni", propietario.getDni())
               .addParameter("nombre", propietario.getNombre())
               .addParameter("email", propietario.getEmail())
               .addParameter("celular", propietario.getCelular())
               .addParameter("fechaNac", propietario.getFechaNac() != null ? java.sql.Date.valueOf(propietario.getFechaNac()) : null)
               .addParameter("username", propietario.getUsername())
               .addParameter("cuil", propietario.getCuil())
               .addParameter("password", propietario.getPassword())
               .addParameter("direccion", propietario.getDireccion())
               .executeUpdate();

            String sqlProp = "INSERT INTO propietario(dniPropietario, cbu) VALUES(:dni, :cbu)";

            con.createQuery(sqlProp)
               .addParameter("dni", propietario.getDni())
               .addParameter("cbu", propietario.getCbu())
               .executeUpdate();
            con.commit();

            return propietario;
        }
    }
}