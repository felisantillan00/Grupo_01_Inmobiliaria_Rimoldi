package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Inquilino;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.data.Table;
import java.util.Map;

@Repository
public class InquilinoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();
    
    public Inquilino findById(Long dniInquilino) {
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT p.dni, p.nombre, p.email, p.celular, p.fecha_nac as fechaNac,
                    p.cuil, p.direccion, i.mascotas, i.empresa_trabaja, 
                    i.cantidad_integrantes, i.ingresos, i.tieneRecargo, i.valorDeDeuda
                FROM inquilino i
                JOIN persona p ON i.dniInquilino = p.dni
                WHERE i.dniInquilino = :dniInquilino
            """;

            Table table = con.createQuery(sql)
                            .addParameter("dniInquilino", dniInquilino)
                            .executeAndFetchTable();

            if (table.rows().isEmpty()) return null;

            Map<String, Object> row = table.rows().get(0).asMap();
            Inquilino inquilino = new Inquilino();
            inquilino.setDni(row.get("dni") != null ? ((Number) row.get("dni")).longValue() : null);
            inquilino.setNombre((String) row.get("nombre"));
            inquilino.setEmail((String) row.get("email"));
            inquilino.setCelular((String) row.get("celular"));
            inquilino.setFechaNac(row.get("fechaNac") != null ? ((java.time.LocalDateTime) row.get("fechaNac")).toLocalDate() : null);
            inquilino.setCuil(row.get("cuil") != null ? ((Number) row.get("cuil")).longValue() : null);
            inquilino.setDireccion((String) row.get("direccion"));
            inquilino.setMascotas(row.get("mascotas") != null && ((Number) row.get("mascotas")).intValue() != 0);
            inquilino.setEmpresa_trabaja((String) row.get("empresa_trabaja"));
            inquilino.setCantidad_integrantes((String) row.get("cantidad_integrantes"));
            inquilino.setIngresos(row.get("ingresos") != null ? ((Number) row.get("ingresos")).doubleValue() : 0);
            inquilino.setTieneRecargo(row.get("tieneRecargo") != null && ((Number) row.get("tieneRecargo")).intValue() == 1);
            inquilino.setValorDeDeuda(row.get("valorDeDeuda") != null ? ((Number) row.get("valorDeDeuda")).doubleValue() : 0);
            System.err.println(inquilino.getDni());
            return inquilino;
        }
    }

    public Inquilino findByRecargo(Long dniInquilino){
        try (Connection con = sql2o.open()) {
            String sql = """
                SELECT 
                    dniInquilino,
                    cantidad_integrantes,
                    valorDeDeuda
                FROM inquilino
                WHERE dniInquilino = :dniInquilino
            """;
            return con.createQuery(sql)
                    .addParameter("dniInquilino", dniInquilino)
                    .executeAndFetchFirst(Inquilino.class);
        }
    }

    // Funcion para ponerle recargo a un inquilino
    public void updateRecargo(Inquilino inquilino){
        try (Connection con = sql2o.open()) {
            String sql = """
                UPDATE inquilino
                SET tieneRecargo = :tieneRecargo, valorDeDeuda = :valorDeDeuda
                WHERE dniInquilino = :dniInquilino
            """;
            con.createQuery(sql)
                .addParameter("tieneRecargo", inquilino.isTieneRecargo())
                .addParameter("valorDeDeuda", inquilino.getValorDeDeuda())
                .addParameter("dniInquilino", inquilino.getDni())
                .executeUpdate();
        }
    }
}