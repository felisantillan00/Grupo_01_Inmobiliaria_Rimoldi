package ar.edu.unlpam.ing.inmobiliaria_rimoldi.service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import org.springframework.stereotype.Service;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.dao.*;
import ar.edu.unlpam.ing.inmobiliaria_rimoldi.model.*;

@Service
public class ContratoService {
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private PropiedadDAO propiedadDAO;
    @Autowired
    private ComercialDAO comercialDAO;
    @Autowired
    private FamiliarDAO familiarDAO;
    @Autowired
    private PropietarioDAO propietarioDAO;
    @Autowired
    private InquilinoDAO inquilinoDAO;
    @Autowired
    private MartilleroDAO martilleroDAO;
    @Autowired
    private GaranteDAO garanteDAO;
    @Autowired
    private EstadoContratoDAO estadoContratoDAO;
    @Autowired
    private EstadoDAO estadoDAO;

    @Transactional
    public Contrato altaContrato(Contrato contrato) {
        // Buscar la propiedad base
        System.err.println("Ingreso al altaContrato");
        Propiedad propiedad = propiedadDAO.findById(contrato.getIdPropiedad());
        if (propiedad == null) {
            throw new RuntimeException("No existe una propiedad registrada con ID: " + contrato.getIdPropiedad());
        }
        System.err.println("ID propiedad: " + contrato.getIdPropiedad());

        // Verificar tipo (Familiar o Comercial)
        Comercial comercial = comercialDAO.findById(contrato.getIdPropiedad());
        Familiar familiar = familiarDAO.findById(contrato.getIdPropiedad());
        if (comercial == null && familiar == null) {
            throw new RuntimeException("La propiedad no tiene tipo asociado (ni Comercial ni Familiar).");
        }
        System.out.println("Paso la verificacion del tipo de propiedad");

        // Verificar contrato vigente
        if (contratoDAO.existeContratoVigente(propiedad.getIdPropiedad())) {
            throw new RuntimeException("La propiedad seleccionada ya posee un contrato vigente y no puede generar uno nuevo.");
        }
        System.out.println("Paso la verificacion de contrato vigente");

        // Buscar propietario
        Propietario propietario = propietarioDAO.findById(propiedad.getDniPropietario());
        if (propietario == null) {
            throw new RuntimeException("No existe un propietario registrado con DNI: " + propiedad.getDniPropietario());
        }
        System.out.println("Paso la verificacion del propietario " + propietario.getDni());

        // Buscar inquilino
        Inquilino inquilino = inquilinoDAO.findById(contrato.getDniInquilino());
        if (inquilino == null) {
            throw new RuntimeException("No existe un inquilino registrado con DNI: " + contrato.getDniInquilino());
        }
        System.out.println("Paso la verificacion del inquilino " + inquilino.getDni());

        // Buscar martillero
        Martillero martillero = martilleroDAO.findById(contrato.getDniMartillero());
        if (martillero == null) {
            throw new RuntimeException("No existe un martillero registrado con DNI: " + contrato.getDniMartillero());
        }
        System.out.println("Paso la verificacion del martillero " + martillero.getDni());

        // Buscar garante
        Garante garante = garanteDAO.findById(contrato.getDniGarante());
        if (garante == null) {
            throw new RuntimeException("No existe un garante registrado con DNI: " + contrato.getDniGarante());
        }
        System.out.println("Paso la verificacion del garante " + garante.getDni());

        // Crear contrato
        contrato.setDniPropietario(propiedad.getDniPropietario());
        
        contratoDAO.save(contrato);
        // Crear estado inicial del contrato
        // Buscamos el ID Activo"
        Estado estadoActivo = estadoDAO.findByNombre("Activo");
        EstadoContrato estadoInicial = new EstadoContrato();
        estadoInicial.setNroContrato(contrato.getNroContrato());
        estadoInicial.setIdEstado(estadoActivo.getIdEstado());
        estadoInicial.setFechaEstado(new Date());
        // Guardar el estado inicial
        estadoContratoDAO.save(estadoInicial);

        return contrato;
    }
}