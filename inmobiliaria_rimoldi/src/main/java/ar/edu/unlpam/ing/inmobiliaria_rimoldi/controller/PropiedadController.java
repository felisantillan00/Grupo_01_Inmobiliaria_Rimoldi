package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propiedad;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.service.PropiedadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/propiedades")
public class PropiedadController {
    @Autowired
    private PropiedadService propiedadService;
    private static final Logger logger = LoggerFactory.getLogger(PropiedadController.class);
    @PostMapping
    public ResponseEntity<?> altaPropiedad(@RequestBody Propiedad propiedad) {
        try {
            Propiedad nueva = propiedadService.altaPropiedad(propiedad);
            logger.info("Propiedad registrada: {}", nueva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva); 
            // 201 Created
        } catch (IllegalArgumentException e) {
            logger.warn("Error de negocio al registrar propiedad: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage()); 
            // 400 Bad Request
        } catch (RuntimeException e) {
            logger.warn("Conflicto al registrar propiedad: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Error de negocio: " + e.getMessage());
        }catch (Exception e) {
            logger.error("Error inesperado al registrar propiedad", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al crear la propiedad: " + e.getMessage());
            // 500 Internal Server Error
        }
    }
}