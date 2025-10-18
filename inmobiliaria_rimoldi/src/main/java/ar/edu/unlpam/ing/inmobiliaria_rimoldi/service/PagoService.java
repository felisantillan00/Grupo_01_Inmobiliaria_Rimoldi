package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class PagoService {
    @Autowired
    private PagoDAO pagoDAO;
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private DetallePagoDAO detallePagoDAO;
    @Autowired
    private InquilinoDAO inquilinoDAO;

    @Transactional
    public Pago registrarPago(Pago pagoInput) {
        // 1. Verificar que el contrato exista
        Contrato contrato = contratoDAO.findById(pagoInput.getNroContrato());
        if (contrato == null) {
            throw new RuntimeException("No existe el contrato con nro: " + pagoInput.getNroContrato());
        }
        System.err.println("El contrato" + contrato.getNroContrato());
        System.out.println("dni inquilino por contrato" + contrato.getDniInquilino());
        // 2. Traer el inquilino asociadoc
        System.err.println("Buscando inquilino con DNI: " + contrato.getDniInquilino());
        Inquilino inquilino = inquilinoDAO.findById(contrato.getDniInquilino());
        if (inquilino == null) {
            throw new RuntimeException("No se encontró el inquilino asociado al contrato.");
        }
        // 3. Obtengo fecha actual
        Date fechaPago = new Date(); // fecha actual
        double total = 0.0;
        // 4. Calcular vencimiento
        Calendar cal = Calendar.getInstance();
        cal.setTime(contrato.getFechaInicio());
        while (cal.getTime().before(fechaPago)) {
            cal.add(Calendar.MONTH, 1);
        }
        Date fechaVencimiento = cal.getTime();
        // 4. Crear el pago
        Pago pago = new Pago();
        pago.setFechaPago(fechaPago);
        pago.setNroContrato(contrato.getNroContrato());
        // 5. Cobrar recargo pendiente del mes pasado si lo tiene
        if (inquilino.isTieneRecargo()) {
            total += inquilino.getValorDeDeuda();
            inquilino.setTieneRecargo(false);
            inquilino.setValorDeDeuda(0.0);
            // actualizar inquilino en la base de datos
            inquilinoDAO.updateRecargo(inquilino);
        }
        // 6. Sumar el monto de los detalles del pago actual
        double montoDetalles = pagoInput.getDetalles().stream()
                                .mapToDouble(DetallePago::getMonto)
                                .sum();
        total += montoDetalles;

        // 7. Calcular intereses si el pago se hace después del vencimiento
        if (fechaPago.after(fechaVencimiento)) {
            long diasAtraso = ChronoUnit.DAYS.between(
                fechaVencimiento.toInstant(), fechaPago.toInstant()
            );
            double intereses = total * 0.03 * diasAtraso; // 3% diario
            // Guardar el interés en inquilino para el próximo mes
            inquilino.setTieneRecargo(true);
            inquilino.setValorDeDeuda(intereses);
            inquilinoDAO.updateRecargo(inquilino);
        }

        pago.setMontoTotal(total);

        // 8. Guardar el pago
        pagoDAO.save(pago);

        // 9. Guardar detalles asociados
        for (DetallePago detalle : pagoInput.getDetalles()) {
            detalle.setIdPago(pago.getIdPago());
            detallePagoDAO.save(detalle);
        }

        pago.setDetalles(pagoInput.getDetalles());
        return pago;
    }

    public List<Pago> buscarPagos(
        LocalDate fechaDesde, LocalDate fechaHasta,
        Long dniInquilino, Long dniPropietario, Integer nroContrato,
        Double montoMin, Double montoMax, String estado, String sortBy, String sortOrder
    ) {
        List<Pago> pagos = pagoDAO.findByFiltros(
            fechaDesde, fechaHasta,
            dniInquilino, dniPropietario, nroContrato,
            montoMin, montoMax, estado, sortBy, sortOrder
        );

        for (Pago pago : pagos) {
            List<DetallePago> detalles = detallePagoDAO.findByIdPago(pago.getIdPago());
            pago.setDetalles(detalles);
        }
        return pagos;
    }
}