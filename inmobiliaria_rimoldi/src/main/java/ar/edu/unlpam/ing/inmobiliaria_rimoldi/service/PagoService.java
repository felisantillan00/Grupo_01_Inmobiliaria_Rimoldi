package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import jakarta.transaction.Transactional;

@Service
public class PagoService {
    @Autowired
    private PagoDAO pagoDAO;
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private DetallePagoDAO detallePagoDAO;

    @Transactional
    public Pago registrarPago(Pago pagoInput) {
        // 1. Verificar que el contrato exista
        Contrato contrato = contratoDAO.findById(pagoInput.getNroContrato());
        if (contrato == null) {
            throw new RuntimeException("No existe el contrato con nro: " + pagoInput.getNroContrato());
        }

        Date fechaPago = new Date(); // fecha actual
        double total = 0.0;
        // calcular vencimiento
        Calendar cal = Calendar.getInstance();
        cal.setTime(contrato.getFechaInicio());
        while (cal.getTime().before(fechaPago)) {
            cal.add(Calendar.MONTH, 1);
        }
        Date fechaVencimiento = cal.getTime();
        // 2. Crear el pago usando solo nroContrato
        Pago pago = new Pago();
        pago.setFechaPago(fechaPago);
        pago.setNroContrato(contrato.getNroContrato());
        // 3. Calcular intereses y monto final por detalle
        for (DetallePago detalle : pagoInput.getDetalles()) {
            double intereses = 0.0;
            if (fechaPago.after(fechaVencimiento)) {
                long diasAtraso = ChronoUnit.DAYS.between(
                    fechaVencimiento.toInstant(), fechaPago.toInstant()
                );
                intereses = detalle.getMonto() * 0.0033 * diasAtraso;
            }

            double montoFinal = detalle.getMonto() + intereses;
            total += montoFinal;

            detalle.setIntereses(intereses);
            detalle.setMontoFinal(montoFinal);
        }
        pago.setMontoTotal(total);
        // 4. Guardar el pago
        pagoDAO.save(pago);
        // 5. Guardar detalles asociados
        for (DetallePago detalle : pagoInput.getDetalles()) {
            detalle.setIdPago(pago.getIdPago()); // asignar el id del pago guardado
            detallePagoDAO.save(detalle);
        }
        pago.setDetalles(pagoInput.getDetalles());
        return pago;
    }
}