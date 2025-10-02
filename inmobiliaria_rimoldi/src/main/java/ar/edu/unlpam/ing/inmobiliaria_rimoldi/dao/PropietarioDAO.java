package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propietario;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.ResultSetHandler;

@Repository
public class PropietarioDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Propietario findById(Long dni) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT p.dni,
                    p.nombre,
                    p.email,
                    p.celular,
                    p.fecha_nac,
                    p.username,
                    p.cuil,
                    p.password,
                    p.direccion,
                    pr.cbu
                FROM persona p
                JOIN propietario pr ON p.dni = pr.dniPropietario
                WHERE p.dni = :dni
            """;

            return con.createQuery(sql)
                    .addParameter("dni", dni)
                    .executeAndFetchFirst((ResultSetHandler<Propietario>) rs -> {
                        Propietario prop = new Propietario();
                        prop.setDni(rs.getLong("dni"));
                        prop.setNombre(rs.getString("nombre"));
                        prop.setEmail(rs.getString("email"));
                        prop.setCelular(rs.getString("celular"));

                        java.sql.Date sqlDate = rs.getDate("fecha_nac");
                        if (sqlDate != null) {
                            prop.setFechaNac(sqlDate.toLocalDate()); // LocalDate
                        }
                        prop.setUsername(rs.getString("username"));
                        prop.setCuil(rs.getLong("cuil"));
                        prop.setPassword(rs.getString("password"));
                        prop.setDireccion(rs.getString("direccion"));
                        prop.setCbu(rs.getLong("cbu"));

                        return prop;
                    });
        }
    }


    // Insertar nuevo propietario (dos tablas)
    public Propietario save(Propietario propietario) {
        try (Connection con = sql2o.beginTransaction()) {
            String sqlPersona = "INSERT INTO persona(dni, nombre, email, celular, fecha_nac, username, cuil, password, direccion) " +
                                "VALUES(:dni, :nombre, :email, :celular, :fechaNac, :username, :cuil, :password, :direccion)";
            con.createQuery(sqlPersona)
               .addParameter("dni", propietario.getDni())
               .addParameter("nombre", propietario.getNombre())
               .addParameter("email", propietario.getEmail())
               .addParameter("celular", propietario.getCelular())
               .addParameter("fechaNac", propietario.getFechaNac())
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