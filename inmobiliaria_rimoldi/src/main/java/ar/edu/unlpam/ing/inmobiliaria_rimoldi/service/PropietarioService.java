package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.PropietarioDAO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propietario;
import jakarta.transaction.Transactional;
@Service
public class PropietarioService {
    @Autowired
    private PropietarioDAO propietarioDAO;

    @Transactional
    public Propietario altaPersona(Propietario Propietario) {
        // Solo crea o actualiza
        return propietarioDAO.save(Propietario);
    }

    public Propietario buscarPorDni(Long dni) {
        return propietarioDAO.findById(dni);
    }
}