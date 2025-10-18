package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Contrato;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.service.ContratoService;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public ResponseEntity<?> registrarContrato(@RequestBody Contrato contrato) {
        try {
            Contrato contratoNuevo = contratoService.altaContrato(contrato);
            return ResponseEntity.status(HttpStatus.CREATED).body(contratoNuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
            // 400 Bad Request
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error de negocio: " + e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear la propiedad: " + e.getMessage());
            // 500 Internal Server Error
        }
    }
}