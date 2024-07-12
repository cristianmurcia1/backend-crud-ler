package com.example.backend_crud.persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/v1/usuarios")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService userService) {
        this.personaService = userService;
    }

    // Obtener los usuarios
    @GetMapping
    public List<Persona> obtenerPersonas() {
        return this.personaService.obtenerPersonas();
    }

    // Registrar persona
    @PostMapping
    public ResponseEntity<Object> registrarPersona(@RequestBody Persona persona) {
        return this.personaService.registrarPersona(persona);
    }

    // Actualizar persona
    @PutMapping
    public ResponseEntity<Object> actualizarPersona(@RequestBody Persona persona){
        // Se emple el mismo método empleado para crear un producto
        return this.personaService.registrarPersona(persona);
    }

    // Eliminar persona
    @DeleteMapping(path = "{personId}")
    public ResponseEntity<Object> eliminarPersona(@PathVariable("personId") Long id){
        return this.personaService.eliminarPersona(id);
    }

}
