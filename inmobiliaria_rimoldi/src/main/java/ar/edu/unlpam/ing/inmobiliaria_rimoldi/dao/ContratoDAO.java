package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Contrato;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.*;
import java.lang.reflect.Field;
// import org.sql2o.data.Row;


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
            contrato.setFechaInicio(row.getDate("fecha_inicio"));
            contrato.setFechaFin(row.getDate("fecha_fin"));
            contrato.setFechaCancelacion(row.getDate("fecha_cancelacion"));
            contrato.setIdPropiedad(row.getInteger("idPropiedad"));
            contrato.setDniPropietario(row.getLong("dniPropietario"));
            contrato.setDniInquilino(row.getLong("dniInquilino"));
            contrato.setDniMartillero(row.getLong("dniMartillero"));
            contrato.setDniGarante(row.getLong("dniGarante"));
            return contrato;
        }
    }

    // --- Guardar contrato con REFLEXIÓN ---
    public Contrato save(Contrato contrato) {
        try (Connection con = sql2o.open()) {

            // 🧠 1. Generar la query automáticamente leyendo los atributos de la clase
            StringBuilder campos = new StringBuilder();
            StringBuilder valores = new StringBuilder();
            Map<String, Object> parametros = new HashMap<>();

            for (Field field : Contrato.class.getDeclaredFields()) {
                field.setAccessible(true);
                String nombreCampo = field.getName();

                // No incluimos la PK autogenerada
                if (nombreCampo.equalsIgnoreCase("nroContrato")) continue;

                try {
                    Object valor = field.get(contrato);
                    if (valor != null) {
                        if (campos.length() > 0) {
                            campos.append(", ");
                            valores.append(", ");
                        }
                        campos.append(nombreCampo);
                        valores.append(":").append(nombreCampo);
                        parametros.put(nombreCampo, valor);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error al acceder a campo: " + nombreCampo, e);
                }
            }

            String sql = String.format("INSERT INTO contrato (%s) VALUES (%s)", campos, valores);

            // 🧩 2. Crear y ejecutar la query dinámica
            var query = con.createQuery(sql, true);
            for (Map.Entry<String, Object> param : parametros.entrySet()) {
                query.addParameter(param.getKey(), param.getValue());
            }

            Object key = query.executeUpdate().getKey();
            contrato.setNroContrato(((Number) key).intValue());

            System.out.println("🔍 [Reflexivo] Query generada: " + sql);
            return contrato;
        }
    }

    // --- Verificar contrato vigente ---
    public boolean existeContratoVigente(Integer idPropiedad) {
        String sql = """
            SELECT COUNT(*) 
            FROM contrato 
            WHERE idPropiedad = :idPropiedad
            AND fecha_inicio <= NOW()
            AND (fecha_cancelacion IS NULL OR fecha_cancelacion > NOW())
        """;
        try (Connection con = sql2o.open()) {
            Long count = con.createQuery(sql)
                    .addParameter("idPropiedad", idPropiedad)
                    .executeScalar(Long.class);
            return count > 0;
        }
    }
    // public Contrato findById(int nroContrato) {
    //     try (Connection con = sql2o.open()) {
    //         String sql = "SELECT * FROM contrato WHERE nro_contrato = :id";

    //         org.sql2o.data.Table table = con.createQuery(sql)
    //                         .addParameter("id", nroContrato)
    //                         .executeAndFetchTable();

    //         if (table.rows().isEmpty()) return null;

    //         Row row = table.rows().get(0);
    //         Contrato contrato = new Contrato();
    //         contrato.setNroContrato((int) row.getInteger("nro_contrato"));
    //         contrato.setFechaInicio(new java.util.Date(row.getDate("fecha_inicio").getTime()));
    //         contrato.setFechaFin(row.getDate("fecha_fin") != null 
    //             ? new java.util.Date(row.getDate("fecha_fin").getTime())
    //             : null);
    //         contrato.setFechaCancelacion(row.getDate("fecha_cancelacion") != null 
    //             ? new java.util.Date(row.getDate("fecha_cancelacion").getTime())
    //             : null);
    //         contrato.setIdPropiedad(row.getInteger("idPropiedad"));
    //         contrato.setDniPropietario(row.getLong("dniPropietario"));
    //         contrato.setDniInquilino(row.getLong("dniInquilino")); // <-- ¡La clave!
    //         contrato.setDniMartillero(row.getLong("dniMartillero"));
    //         contrato.setDniGarante(row.getLong("dniGarante"));
    //         return contrato;
    //     }
    // }
    
    // // Guardar contrato
    // public Contrato save(Contrato contrato) {
    //     try (Connection con = sql2o.open()) {
    //         String sql = """
    //             INSERT INTO contrato(
    //                 fecha_inicio, fecha_fin, fecha_cancelacion, idPropiedad, dniPropietario, dniInquilino, dniMartillero, dniGarante
    //             ) VALUES (
    //                 :fechaInicio, :fechaFin, :fechaCancelacion, :idPropiedad, :dniPropietario, :dniInquilino, :dniMartillero, :dniGarante
    //             )
    //         """;

    //         Object key = con.createQuery(sql, true)
    //                         .addParameter("fechaInicio", contrato.getFechaInicio())
    //                         .addParameter("fechaFin", contrato.getFechaFin())
    //                         .addParameter("fechaCancelacion", contrato.getFechaCancelacion())
    //                         .addParameter("idPropiedad", contrato.getIdPropiedad())
    //                         .addParameter("dniPropietario", contrato.getDniPropietario())
    //                         .addParameter("dniInquilino", contrato.getDniInquilino())
    //                         .addParameter("dniMartillero", contrato.getDniMartillero())
    //                         .addParameter("dniGarante", contrato.getDniGarante())
    //                         .executeUpdate()
    //                         .getKey();
    //         contrato.setNroContrato(((Number) key).intValue());
    //         return contrato;
    //     }
    // }

    // // Verificar contrato vigente
    // public boolean existeContratoVigente(Integer idPropiedad) {
    //     String sql = """
    //         SELECT COUNT(*) 
    //         FROM contrato 
    //         WHERE idPropiedad = :idPropiedad
    //         AND fecha_inicio <= NOW()
    //         AND (fecha_cancelacion IS NULL OR fecha_cancelacion > NOW())
    //     """;
    //     try (Connection con = sql2o.open()) {
    //         Long count = con.createQuery(sql)
    //                         .addParameter("idPropiedad", idPropiedad)
    //                         .executeScalar(Long.class);
    //         return count > 0;
    //     }
    // }
}