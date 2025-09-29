package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propietario;

@Repository
public class PropietarioDAO {
    @PersistenceContext
    private EntityManager em;

    public Propietario findById(Long dni) {
        return em.find(Propietario.class, dni);
    }

    public Propietario save(Propietario propietario) {
        em.persist(propietario);
        return propietario;
    }

    public Propietario update(Propietario propietario) {
        return em.merge(propietario);
    }

    public void delete(Long dni) {
        Propietario propietario = em.find(Propietario.class, dni);
        if (propietario != null) {
            em.remove(propietario);
        }
    }
}
