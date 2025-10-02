package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;

@Service
public class PropiedadService {
    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    private PropietarioDAO propietarioDAO;

    @Autowired
    private ComercialDAO comercialDAO;

    @Autowired
    private FamiliarDAO familiarDAO;

    @Autowired
    private PropiedadDAO propiedadDAO;

    @Transactional
    public Propiedad altaPropiedad(Propiedad propiedad) {
        // 1. Validar propietario
        Propietario propietario = propietarioDAO.findById(propiedad.getPropietario().getDni());
        if (propietario == null) {
            Persona persona = personaDAO.findById(propiedad.getPropietario().getDni());
            if (persona != null) {
                throw new RuntimeException("La persona existe pero no es propietario. Debe registrar CBU.");
            } else {
                throw new RuntimeException("La persona no está registrada. Debe cargar todos los datos + CBU.");
            }
        }
        propiedad.setPropietario(propietario);

        // 2. Guardar la propiedad en PropiedadDAO
        Propiedad propGuardada = propiedadDAO.save(propiedad); // devuelve Propiedad con id generado

        // 3. Guardar en tabla específica según tipo
        if (propiedad instanceof Comercial) {
            Comercial comercial = (Comercial) propiedad;
            comercial.setIdPropiedad(propGuardada.getIdPropiedad()); // importante para la FK
            comercialDAO.save(comercial); // solo inserta en comercial
            return comercial;
        } else if (propiedad instanceof Familiar) {
            Familiar familiar = (Familiar) propiedad;
            familiar.setIdPropiedad(propGuardada.getIdPropiedad());
            familiarDAO.save(familiar); // solo inserta en familiar
            return familiar;
        } else {
            throw new RuntimeException("Tipo de propiedad inválido: " + propiedad.getClass().getSimpleName());
        }
    }
}