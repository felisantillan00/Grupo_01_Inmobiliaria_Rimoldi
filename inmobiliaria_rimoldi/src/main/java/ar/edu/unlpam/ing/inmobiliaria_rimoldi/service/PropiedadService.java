package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
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
        // Validar propietario
        Propietario propietario = propietarioDAO.findById(propiedad.getDniPropietario());
        if (propietario == null) {
            Persona persona = personaDAO.findById(propiedad.getDniPropietario());
            if (persona != null) {
                throw new RuntimeException("La persona existe pero no es propietario. Debe registrar CBU.");
            } else {
                throw new RuntimeException("La persona no está registrada. Debe cargar todos los datos + CBU.");
            }
        }
        // Guardar subclases
        if (propiedad instanceof Familiar || propiedad instanceof Comercial) {
            Propiedad propGuardada = propiedadDAO.save(propiedad);
            if (propiedad instanceof Comercial) {
                Comercial comercial = (Comercial) propiedad;
                comercial.setIdPropiedad(propGuardada.getIdPropiedad());
                comercialDAO.save(comercial);
                return comercial;
            } else if (propiedad instanceof Familiar) {
                Familiar familiar = (Familiar) propiedad;
                familiar.setIdPropiedad(propGuardada.getIdPropiedad());
                familiarDAO.save(familiar);
                return familiar;
            }
        }
        // Si llega acá, algo está mal porque debería ser Comercial o Familiar
        throw new RuntimeException("La propiedad debe ser del tipo Comercial o Familiar.");
    }
}