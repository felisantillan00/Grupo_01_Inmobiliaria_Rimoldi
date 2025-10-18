package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Pago;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import org.sql2o.*;

@Repository
public class PagoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public Pago save(Pago pago) {
        try (Connection con = sql2o.open()) {
            String sql = "INSERT INTO pago(fecha_pago, monto_total, nro_contrato) " +
                         "VALUES(:fechaPago, :montoTotal, :nroContrato)";
            Object id = con.createQuery(sql, true)
                .addParameter("fechaPago", pago.getFechaPago())
                .addParameter("montoTotal", pago.getMontoTotal())
                .addParameter("nroContrato", pago.getNroContrato())
                .executeUpdate()
                .getKey();
            pago.setIdPago(((Number) id).intValue());
            return pago;
        }
    }

    public List<Pago> findByFiltros(
            LocalDate fechaDesde, LocalDate fechaHasta,
            Long dniInquilino, Long dniPropietario, Integer nroContrato,
            Double montoMin, Double montoMax, String estado, String sortBy, String sortOrder
    ) {
        try (Connection con = sql2o.open()) {

            StringBuilder sql = new StringBuilder("""
                SELECT 
                    p.idPago,
                    p.fecha_pago AS fechaPago,
                    p.monto_total AS montoTotal,
                    p.nro_contrato AS nroContrato
                FROM pago p
                INNER JOIN contrato c ON p.nro_contrato = c.nro_contrato
                WHERE 1=1
            """);

            if (fechaDesde != null) sql.append(" AND p.fecha_pago >= :fechaDesde");
            if (fechaHasta != null) sql.append(" AND p.fecha_pago <= :fechaHasta");
            if (dniInquilino != null) sql.append(" AND c.dniInquilino = :dniInquilino");
            if (dniPropietario != null) sql.append(" AND c.dniPropietario = :dniPropietario");
            if (nroContrato != null) sql.append(" AND p.nro_contrato = :nroContrato");
            if (montoMin != null) sql.append(" AND p.monto_total >= :montoMin");
            if (montoMax != null) sql.append(" AND p.monto_total <= :montoMax");
            if (estado != null && !estado.isEmpty()) sql.append(" AND p.estado = :estado");

            // Ordenamiento opcional
            if (sortBy != null && !sortBy.isEmpty()) {
                sql.append(" ORDER BY ").append(sortBy);
                if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                    sql.append(" DESC");
                } else {
                    sql.append(" ASC");
                }
            }

            Query query = con.createQuery(sql.toString());

            if (fechaDesde != null) query.addParameter("fechaDesde", fechaDesde);
            if (fechaHasta != null) query.addParameter("fechaHasta", fechaHasta);
            if (dniInquilino != null) query.addParameter("dniInquilino", dniInquilino);
            if (dniPropietario != null) query.addParameter("dniPropietario", dniPropietario);
            if (nroContrato != null) query.addParameter("nroContrato", nroContrato);
            if (montoMin != null) query.addParameter("montoMin", montoMin);
            if (montoMax != null) query.addParameter("montoMax", montoMax);
            if (estado != null && !estado.isEmpty()) query.addParameter("estado", estado);

            return query.executeAndFetch(Pago.class);
        }
    }
}