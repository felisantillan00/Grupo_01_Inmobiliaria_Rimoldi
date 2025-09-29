package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.PersonaDAO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Persona;
import jakarta.transaction.Transactional;

@Service
public class PersonaService {
    @Autowired
    private PersonaDAO personaDAO;

    @Transactional
    public Persona altaPersona(Persona persona) {
        // Solo crea o actualiza
        return personaDAO.save(persona);
    }

    public Persona buscarPorDni(Long dni) {
        return personaDAO.findById(dni);
    }
}