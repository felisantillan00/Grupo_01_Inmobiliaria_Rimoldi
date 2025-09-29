package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Persona;

@Repository
public class PersonaDAO {
    @PersistenceContext
    private EntityManager em;

    public Persona findById(Long dni) {
        return em.find(Persona.class, dni);
    }

    public Persona save(Persona persona) {
        em.persist(persona);
        return persona;
    }

    public Persona update(Persona persona) {
        return em.merge(persona);
    }

    public void delete(Long dni) {
        Persona persona = em.find(Persona.class, dni);
        if (persona != null) {
            em.remove(persona);
        }
    }
}