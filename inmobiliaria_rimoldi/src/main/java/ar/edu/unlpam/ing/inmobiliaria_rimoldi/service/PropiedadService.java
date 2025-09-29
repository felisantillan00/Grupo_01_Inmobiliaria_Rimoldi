package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.PersonaDAO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.PropiedadDAO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.PropietarioDAO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dto.AltaPropiedadDTO;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Comercial;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Familiar;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Persona;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propiedad;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.Propietario;

@Service
public class PropiedadService {
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private PropietarioDAO propietarioDAO;
    @Autowired
    private PropiedadDAO propiedadDAO;

    @Transactional
    public Propiedad altaPropiedad(AltaPropiedadDTO dto) {
        // 1. Buscar propietario
        Propietario propietario = propietarioDAO.findById(dto.getDniPropietario());
        if (propietario == null) {
            Persona persona = personaDAO.findById(dto.getDniPropietario());
            if (persona != null) {
                throw new RuntimeException("La persona existe pero no es propietario. Debe registrar CBU.");
            } else {
                throw new RuntimeException("La persona no está registrada. Debe cargar todos los datos + CBU.");
            }
        }
        // 2. Crear propiedad según el tipo
        Propiedad propiedad;
        if ("comercial".equalsIgnoreCase(dto.getTipoPropiedad())) {
            Comercial comercial = new Comercial();
            comercial.setPermisosMunicipales(Boolean.TRUE.equals(dto.getPermisosMunicipales()));
            comercial.setBaño(Boolean.TRUE.equals(dto.getBanio()));
            comercial.setCocina(Boolean.TRUE.equals(dto.getCocina()));
            comercial.setVidriera(Boolean.TRUE.equals(dto.getVidriera()));
            comercial.setDeposito(Boolean.TRUE.equals(dto.getDeposito()));
            propiedad = comercial;
        } else if ("familiar".equalsIgnoreCase(dto.getTipoPropiedad())) {
            Familiar familiar = new Familiar();
            familiar.setCantAmbientes(dto.getCantAmbientes());
            familiar.setCantBaños(dto.getCantBanios());
            familiar.setCantCocheras(dto.getCantCocheras());
            familiar.setPiscina(Boolean.TRUE.equals(dto.getPiscina()));
            familiar.setPermiteMascota(Boolean.TRUE.equals(dto.getPermiteMascota()));
            familiar.setPermiteNinos(Boolean.TRUE.equals(dto.getPermiteNinos()));
            propiedad = familiar;
        } else {
            throw new RuntimeException("Tipo de propiedad inválido: " + dto.getTipoPropiedad());
        }

        // 3. Setear atributos comunes
        propiedad.setDireccion(dto.getDireccion());
        propiedad.setM2Cubiertos(dto.getM2Cubiertos());
        propiedad.setM2Descubiertos(dto.getM2Descubiertos());
        propiedad.setCondicionesGarantes(dto.getCondicionesGarantes());
        propiedad.setExpensas(dto.getExpensas());
        propiedad.setGastos(dto.getGastos());
        propiedad.setFechaPrecioMinimo(new Date());
        propiedad.setCiudad(dto.getCiudad());
        propiedad.setEnAlquiler("enAlquiler".equalsIgnoreCase(dto.getTipoEstado()));
        propiedad.setEnVenta("enVenta".equalsIgnoreCase(dto.getTipoEstado()));
        propiedad.setValor_alquiler(dto.getValor_alquiler());
        propiedad.setValor_venta(dto.getValor_venta());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setTitulo(dto.getTitulo());
        propiedad.setLatitud(dto.getLatitud());
        propiedad.setLongitud(dto.getLongitud());

        propiedad.setPropietario(propietario);
        // 4. Guardar
        return propiedadDAO.save(propiedad);
    }
}