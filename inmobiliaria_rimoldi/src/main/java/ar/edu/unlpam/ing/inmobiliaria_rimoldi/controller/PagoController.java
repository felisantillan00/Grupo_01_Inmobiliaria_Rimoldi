package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.service.PagoService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<?> registrarPago(@RequestBody Pago pago) {
        try {
            Pago guardado = pagoService.registrarPago(pago);
            if (guardado != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(guardado); 
                // 201 Created
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("No se pudo registrar el pago");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
            // 400 Bad Request
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al registrar el pago: " + e.getMessage());
            // 500 Internal Server Error
        }
    }

    @GetMapping
    public ResponseEntity<?> consultarPagos(
        // 7 filtros posibles
        /*
            * Por fechaDesde y fechaHasta → rango de fechas
            * Por dniInquilino
            * Por dniPropietario
            * Por nroContrato
            * Por montoMin y montoMax → rango de montos
            * Por estado → pendiente, pagado, etc.
        */
            @RequestParam(required = false) LocalDate fechaDesde,
            @RequestParam(required = false) LocalDate fechaHasta,
            @RequestParam(required = false) Long dniInquilino,
            @RequestParam(required = false) Long dniPropietario,
            @RequestParam(required = false) Integer nroContrato,
            @RequestParam(required = false) Double montoMin,
            @RequestParam(required = false) Double montoMax,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
    ) {
        try {
            // El service devuelve la lista de pagos filtrados según los parámetros
            List<Pago> pagos = pagoService.buscarPagos(
                    fechaDesde, fechaHasta,
                    dniInquilino, dniPropietario, nroContrato,
                    montoMin, montoMax, estado, sortBy, sortOrder
            );

            if (pagos.isEmpty()) {
                return ResponseEntity.ok("No se encontraron registros para los criterios seleccionados");
            }

            return ResponseEntity.ok(pagos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al consultar pagos: " + e.getMessage());
        }
    }
}