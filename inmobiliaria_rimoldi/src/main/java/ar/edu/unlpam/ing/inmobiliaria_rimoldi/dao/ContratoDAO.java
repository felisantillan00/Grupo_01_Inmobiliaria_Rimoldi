package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Contrato;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.*;
import java.lang.reflect.Field;

@Repository
public class ContratoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    // --- Buscar contrato por ID ---
    public Contrato findById(int nroContrato) {
        try (Connection con = sql2o.open()) {
            String sql = "SELECT * FROM contrato WHERE nro_contrato = :id";

            var table = con.createQuery(sql)
                    .addParameter("id", nroContrato)
                    .executeAndFetchTable();

            if (table.rows().isEmpty()) return null;

            var row = table.rows().get(0);
            Contrato contrato = new Contrato();
            contrato.setNroContrato(row.getInteger("nro_contrato"));
            contrato.setFechaInicio(row.getDate("fechaInicio"));
            contrato.setFechaFin(row.getDate("fechaFin"));
            contrato.setFechaCancelacion(row.getDate("fechaCancelacion"));
            contrato.setIdPropiedad(row.getInteger("idPropiedad"));
            contrato.setDniPropietario(row.getLong("dniPropietario"));
            contrato.setDniInquilino(row.getLong("dniInquilino"));
            contrato.setDniMartillero(row.getLong("dniMartillero"));
            contrato.setDniGarante(row.getLong("dniGarante"));
            return contrato;
        }
    }

    // --- Insertar contrato con patrón Reflexivo  ---
    public Contrato save(Contrato contrato) {
        try (Connection con = sql2o.open()) {
            // 1️. Inicialización de estructuras para construir la query dinámica
            StringBuilder campos = new StringBuilder();
            StringBuilder valores = new StringBuilder();
            Map<String, Object> parametros = new HashMap<>();

            // 2️. Reflexión: recorremos dinámicamente los atributos del modelo Contrato
            for (Field field : Contrato.class.getDeclaredFields()) {
                field.setAccessible(true); // permite acceder a atributos privados
                String nombreCampo = field.getName(); // obtiene el nombre exacto del atributo

                // 3. Saltamos la PK 'nroContrato' porque es autogenerada
                if (nombreCampo.equalsIgnoreCase("nroContrato")) continue;

                try {
                    // 4️. Obtenemos el valor del campo en el objeto 'contrato'
                    Object valor = field.get(contrato);
                    if (valor != null) {
                        // 5️. Agregamos separadores de coma (,) entre columnas
                        if (campos.length() > 0) {
                            campos.append(", ");
                            valores.append(", ");
                        }
                        // 6️. Convertimos camelCase → snake_case para la BD
                        // String nombreColumna = toSnakeCase(nombreCampo);
                        // 7️. Armamos las partes del INSERT dinámicamente
                        campos.append(nombreCampo);
                        valores.append(":").append(nombreCampo);
                        parametros.put(nombreCampo, valor);
                    }
                } catch (IllegalAccessException e) {
                    // Si no se puede acceder al atributo (no debería pasar)
                    throw new RuntimeException("Error al acceder a campo: " + nombreCampo, e);
                }
            }
            // 8️. Construcción final de la query SQL dinámica
            String sql = String.format("INSERT INTO contrato (%s) VALUES (%s)", campos, valores);

            // 9️. Se crea la query Sql2o y se cargan los parámetros reales
            var query = con.createQuery(sql, true);
            for (Map.Entry<String, Object> param : parametros.entrySet()) {
                query.addParameter(param.getKey(), param.getValue());
            }
            // 10. Se ejecuta la query y se recupera la PK autogenerada
            Object key = query.executeUpdate().getKey();
            contrato.setNroContrato(((Number) key).intValue());

            System.out.println("✅ Query generada con reflexión: " + sql);
            return contrato;
        }
    }

    // Método auxiliar para convertir camelCase a snake_case
    private String toSnakeCase(String camelCase) {
        return camelCase
                .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toLowerCase();
    }

    // --- Verificar contrato vigente ---
    public boolean existeContratoVigente(Integer idPropiedad) {
        String sql = """
            SELECT COUNT(*) 
            FROM contrato 
            WHERE idPropiedad = :idPropiedad
            AND fechaInicio <= NOW()
            AND (fechaCancelacion IS NULL OR fechaCancelacion > NOW())
        """;
        try (Connection con = sql2o.open()) {
            Long count = con.createQuery(sql)
                    .addParameter("idPropiedad", idPropiedad)
                    .executeScalar(Long.class);
            return count > 0;
        }
    }
}