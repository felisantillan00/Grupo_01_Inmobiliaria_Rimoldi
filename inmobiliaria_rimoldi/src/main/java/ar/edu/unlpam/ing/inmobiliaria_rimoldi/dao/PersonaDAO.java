package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Persona;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PersonaDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    // Buscar por DNI
    public Persona findById(Long dni) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM persona WHERE dni = :dni";
            return con.createQuery(sql)
                      .addParameter("dni", dni)
                      .executeAndFetchFirst(Persona.class);
        }
    }
    // Insertar
    public Persona save(Persona persona) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO persona(dni, nombre, email, celular, fecha_nac, username, cuil, password, direccion) " +
                         "VALUES(:dni, :nombre, :email, :celular, :fechaNac, :username, :cuil, :password, :direccion)";
            con.createQuery(sql)
               .addParameter("dni", persona.getDni())
               .addParameter("nombre", persona.getNombre())
               .addParameter("email", persona.getEmail())
               .addParameter("celular", persona.getCelular())
               .addParameter("fechaNac", persona.getFechaNac())
               .addParameter("username", persona.getUsername())
               .addParameter("cuil", persona.getCuil())
               .addParameter("password", persona.getPassword())
               .addParameter("direccion", persona.getDireccion())
               .executeUpdate();
            return persona;
        }
    }
}