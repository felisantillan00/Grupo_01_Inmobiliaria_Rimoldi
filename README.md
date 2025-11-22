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

El proyecto se centra en aplicar conceptos de análisis, diseño y arquitectura de software, incorporando:

- Patrón **DAO**  
- Patrón arquitectónico **MVC**  
- Patrón **Reflexivo**  
- Patrones de diseño utilizados (Singleton, Inyección de Dependencias)  
- Vistas arquitectónicas **Módulo** y **Componente–Conector** según ISO/IEC/IEEE 42010  

---

## 🧱 Arquitectura del Sistema

El sistema está organizado mediante el patrón **MVC**, el cual se considera un **patrón arquitectónico**, no un patrón de diseño.

### ✔ Modelo  
Contiene las entidades principales del dominio:  
`Propiedad`, `Contrato`, `Usuario`, `Pago`, `Inquilino`, `Propietario`, `Martillero`, etc.

### ✔ Persistencia (DAO)  
Responsable del acceso a datos utilizando **SQL2O**.  
Incluye consultas, validaciones, inserciones, actualizaciones y borrados.

### ✔ Servicios  
Procesan la lógica de negocio:  
validaciones, cálculos de recargos, verificación de mora.

### ✔ Controladores  
Exponen endpoints REST que permiten la comunicación con el cliente web.

### ✔ Base de Datos  
Implementada en **MySQL**, con un esquema relacional acorde al dominio.

---

## 🧩 Patrones de Diseño Aplicados

### 🔹 Patrón DAO  
Permite desacoplar la lógica de acceso a datos del resto del sistema.

### 🔹 Patrón Singleton (para SQL2O)  
SQL2O se gestiona mediante una **única instancia compartida**, evitando duplicación de conexiones y manteniendo consistencia.

### 🔹 Inyección de Dependencias  
Utilizada por Spring Boot para desacoplar Controladores, Servicios y DAOs, facilitando el mantenimiento y testeo.

---

## 🔮 Posibles Patrones para Futuras Versiones

### 🔹 Factory Method  
Útil si el sistema incorpora distintos tipos de pagos o contratos.  
Ejemplos futuros:

- `PagoEfectivo`, `PagoTransferencia`, `PagoTarjeta`  
- `ContratoResidencial`, `ContratoComercial`  

### 🔹 Façade  
Serviría para unificar procesos complejos en una sola operación de alto nivel.  
Por ejemplo:

> Registrar contrato + generar pago inicial + enviar notificación

Esto reduce el acoplamiento entre la capa de presentación y la lógica interna.

---

## 🔎 Funcionalidades Principales

### 1️⃣ Alta de Propiedad  
Registra una nueva propiedad con todos sus atributos.  
Incluye validaciones e información específica según el tipo (familiar o comercial).

### 2️⃣ Registrar Pago  
- Verifica deudas previas  
- Calcula recargos por mora  
- Actualiza `tieneRecargo` y `valorDeDeuda`  
- Registra el pago y muestra detalle  

### 3️⃣ Generación de Contrato  
Asocia propiedad, propietario, inquilino, garante y martillero con fechas y datos correspondientes.

### 4️⃣ Consulta de Historial de Pagos  
Permite filtrar por rango de fechas, inquilino, propietario o contrato.

---

## 🗂 Vistas Arquitectónicas (ISO/IEC/IEEE 42010)

### 🔹 Vista de Módulos  
Representa los paquetes del sistema (`controller`, `service`, `dao`, `model`) y sus relaciones.
![DiagramaPaquete](https://github.com/user-attachments/assets/b5ba8146-2414-4183-951c-79e5373a3d75)

### 🔹 Vista de Componente–Conector  
Muestra cómo los componentes se comunican en tiempo de ejecución.

![DiagramaComponente](https://github.com/user-attachments/assets/e8b11cdc-cc96-4111-92a4-9feec4b85ca9)

---

## 🛠 Tecnologías Utilizadas

| Tecnología | Uso |
|-----------|-----|
| **Java 17** | Lenguaje principal |
| **Spring Boot** | Backend REST |
| **SQL2O** | Persistencia |
| **MySQL** | Base de datos |
| **Maven** | Dependencias |
| **UML** | Modelado del sistema |

---

## 🚀 Cómo Ejecutar el Proyecto

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/felisantillan00/Grupo_01_Inmobiliaria_Rimoldi.git
