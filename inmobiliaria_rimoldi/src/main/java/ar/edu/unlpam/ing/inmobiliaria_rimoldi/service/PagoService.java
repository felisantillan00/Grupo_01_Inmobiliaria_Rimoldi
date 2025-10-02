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
        Contrato contrato = contratoDAO.findById(pagoInput.getContrato().getNroContrato());
        if (contrato == null) {
            throw new RuntimeException("No existe el contrato con nro: " + pagoInput.getContrato().getNroContrato());
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

        Pago pago = new Pago();
        pago.setFechaPago(fechaPago);
        pago.setContrato(contrato);

        // Recorre detalles enviados en JSON
        for (DetallePago detallePago : pagoInput.getDetalles()) {
            double intereses = 0.0;

            if (fechaPago.after(fechaVencimiento)) {
                long diasAtraso = ChronoUnit.DAYS.between(
                    fechaVencimiento.toInstant(), fechaPago.toInstant()
                );
                intereses = detallePago.getMonto() * 0.0033 * diasAtraso;
            }

            double montoFinal = detallePago.getMonto() + intereses;
            total += montoFinal;

            detallePago.setIntereses(intereses);
            detallePago.setMontoFinal(montoFinal);
            detallePago.setPago(pago);
        }

        pago.setMontoTotal(total);
        pago.setDetalles(pagoInput.getDetalles());

        // 1. Guardar el pago
        pagoDAO.save(pago);
        
        // 2. Guardar los detalles asociados
        for (DetallePago detalle : pago.getDetalles()) {
            detallePagoDAO.save(detalle);
        }
        return pago;
    }
}