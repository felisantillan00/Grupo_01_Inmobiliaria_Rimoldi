package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dto.AltaPropiedadDTO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propiedad;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.service.PropiedadService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/propiedades")
public class PropiedadController {
    @Autowired
    private PropiedadService propiedadService;

    @PostMapping
    public Propiedad altaPropiedad(@RequestBody AltaPropiedadDTO dto) {
        return propiedadService.altaPropiedad(dto);
    }
}