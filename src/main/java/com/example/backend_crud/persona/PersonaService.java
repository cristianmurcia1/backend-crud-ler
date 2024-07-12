package com.example.backend_crud.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    // Retornar mensaje en la respuesta de la petición
    HashMap<String, Object> datos;

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Obtener los usuarios
    public List<Persona> obtenerPersonas() {
        return this.personaRepository.findAll();
    }

    // Registrar o modificar una persona
    public ResponseEntity<Object> registrarPersona(Persona persona) {
        Optional<Persona> response = this.personaRepository.findPersonByCedula(persona.getCedula());
        datos = new HashMap<>();

        // Validar con la cédula si ya se encuentra la persona
        if (response.isPresent() && persona.getId() == null) {
            datos.put("error", true);
            datos.put("message", "Ya existe una persona registrada con dicha cédula");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        // Si no se recibe el id el usuario creará una nueva persona
        datos.put("message", "Persona guardada exitosamente");

        // Si en el body se recibe el id el usuario actualizará una persona
        if (persona.getId() != null) {
            datos.put("message", "Datos actualizados exitosamente");
        }

        // .save realiza la operación de "upsert". Es decir, si la entidad no existe en la base de datos, la crea; si ya existe, la actualiza.
        this.personaRepository.save(persona);
        datos.put("data", persona);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    // Eliminar una persona
    public ResponseEntity<Object> eliminarPersona(Long id){
        datos = new HashMap<>();
        boolean validateId = this.personaRepository.existsById(id);

        if (!validateId) {
            datos.put("error", true);
            datos.put("message", "No existe un persona con ese id");
            // Retornar una respuesta personalizada con código de error
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }

        this.personaRepository.deleteById(id);
        datos.put("message", "Persona eliminada exitosamente");
        // Retornar una respuesta personalizada con código de error
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}
