package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.service.PagoService;
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
}