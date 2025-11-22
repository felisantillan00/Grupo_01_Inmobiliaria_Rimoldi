package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;

import org.springframework.stereotype.Repository;
import org.sql2o.*;


import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.EstadoContrato;

@Repository
public class EstadoContratoDAO {
    private Sql2o sql2o = Sql2oDAO.getSql2o();

    public EstadoContrato save(EstadoContrato estadoContrato) {
        try (Connection con = sql2o.open()) {
            String sql = """
                INSERT INTO estadocontrato(nro_contrato, idEstado, fechaEstado)
                VALUES(:nroContrato, :idEstado, :fechaEstado)
            """;
            con.createQuery(sql)
               .addParameter("nroContrato", estadoContrato.getNroContrato())
               .addParameter("idEstado", estadoContrato.getIdEstado())
               .addParameter("fechaEstado", estadoContrato.getFechaEstado())
               .executeUpdate();

            return estadoContrato;
        }
    }
}
