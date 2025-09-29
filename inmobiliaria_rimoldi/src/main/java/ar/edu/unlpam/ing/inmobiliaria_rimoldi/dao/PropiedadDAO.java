package ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propiedad;

@Repository
public class PropiedadDAO {
    @PersistenceContext
    private EntityManager em;

    public Propiedad findById(Integer id) {
        return em.find(Propiedad.class, id);
    }

    public Propiedad save(Propiedad propiedad) {
        em.persist(propiedad);
        return propiedad;
    }

    public Propiedad update(Propiedad propiedad) {
        return em.merge(propiedad);
    }

    public void delete(Integer id) {
        Propiedad propiedad = em.find(Propiedad.class, id);
        if (propiedad != null) {
            em.remove(propiedad);
        }
    }
}
