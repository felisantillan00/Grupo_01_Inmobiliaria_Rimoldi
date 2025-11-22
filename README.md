# 🏠 Inmobiliaria Rimoldi – Sistema de Gestión  
Proyecto universitario desarrollado para la materia **Análisis y Diseño de Sistemas II**, implementado con **Spring Boot**, **SQL2O** y **MySQL**.

---

## 📌 Descripción del Proyecto
El sistema **Inmobiliaria Rimoldi** permite gestionar las operaciones principales de una inmobiliaria real, incluyendo:

- Registro de propiedades  
- Generación de contratos  
- Registro de pagos  
- Consulta de historial de pagos  
- Manejo de propietarios, inquilinos, garantes y martilleros

El foco del proyecto es demostrar el análisis, diseño e implementación aplicando:
- Patrón **DAO**
- Patrón **MVC**
- Patrón **Reflexivo**
- Arquitectura documentada con vistas **Módulo** y **Componente–Conector**

---

## 🧱 Arquitectura del Sistema

El sistema sigue una arquitectura por capas basada en **MVC**:

### ✔ Modelo  
Contiene las entidades del dominio:  
`Propiedad`, `Contrato`, `Usuario`, `Pago`, `Martillero`, `Inquilino`, `Propietario`, etc.

### ✔ Persistencia (DAO)  
Manejo de la base de datos mediante **SQL2O**.  
Incluye consultas, inserciones, actualizaciones y borrados.

### ✔ Servicios  
Encargados de la lógica de negocio:  
validaciones, cálculos de recargos, verificación de pagos fuera de término.

### ✔ Controladores  
Exponen endpoints REST para manejar la interacción con clientes externos.

### ✔ Base de Datos  
MySQL con las tablas correspondientes a las entidades del dominio.

---

## 🔎 Funcionalidades Principales

### 1️⃣ Alta de Propiedad  
Permite registrar una nueva propiedad.  
Incluye validaciones y campos específicos según tipo de propiedad (familiar o comercial).

### 2️⃣ Registrar Pago  
- Verificación de deuda previa  
- Aplicación de recargos por mora  
- Actualización de estados (`tieneRecargo`, `valorDeDeuda`)  
- Registro del pago y generación de detalle  

### 3️⃣ Generación de Contrato  
Asocia propietario, inquilino, garante y propiedad con fechas correspondientes.

### 4️⃣ Consulta de Historial de Pagos  
Permite filtrar por periodo, inquilino, propietario o contrato.

---

## 🗂 Vistas Arquitectónicas (ISO/IEC/IEEE 42010)

### 🔹 Vista de Módulos  
Representación de los paquetes del sistema (`controller`, `service`, `dao`, `model`)  
y sus relaciones estáticas.

> *(Insertar imagen: VistaModuloDelSistema.jpg)*

### 🔹 Vista de Componente–Conector  
Muestra cómo los componentes se comunican en tiempo de ejecución.

> *(Insertar imagen: VistaDeComponenteConectorDelSistema.jpg)*

---

## 🛠 Tecnologías Utilizadas

| Tecnología | Uso |
|-----------|-----|
| **Java 17** | Lenguaje principal |
| **Spring Boot** | Backend REST |
| **SQL2O** | Capa de persistencia |
| **MySQL** | Base de datos |
| **Maven** | Gestión de dependencias |
| **UML** | Modelado del sistema |

---

## 🚀 Cómo Ejecutar el Proyecto

### 1. Clonar el repositorio
```bash
git clone https://github.com/felisantillan00/Grupo_01_Inmobiliaria_Rimoldi.git
