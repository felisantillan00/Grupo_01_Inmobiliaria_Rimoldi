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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/contratos")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;
    private static final Logger logger = LoggerFactory.getLogger(ContratoController.class);

    @PostMapping
    public ResponseEntity<?> registrarContrato(@RequestBody Contrato contrato) {
        try {
            Contrato contratoNuevo = contratoService.altaContrato(contrato);
            logger.info("Contrato registrado: {}", contratoNuevo);
            return ResponseEntity.status(HttpStatus.CREATED).body(contratoNuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); 
            // 400 Bad Request
        } catch (RuntimeException e) {
            logger.warn("Conflicto al registrar contrato: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error de negocio: " + e.getMessage());
        }catch (Exception e) {
            logger.error("Error inesperado al registrar contrato", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear la propiedad: " + e.getMessage());
            // 500 Internal Server Error
        }
    }
}