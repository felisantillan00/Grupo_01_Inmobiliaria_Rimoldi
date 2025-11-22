package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.sql2o.Sql2o;

public class Sql2oDAO {
    static Sql2o sql2o;

    public static Sql2o getSql2o() {
        if (sql2o == null) {
            sql2o = new Sql2o(
                "jdbc:mysql://localhost:3306/rimoldi_inmobiliaria?useSSL=false&serverTimezone=UTC",
                "root",
                ""
            );
        }
        return sql2o;
    }
}