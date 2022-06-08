package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller // Para que spring pueda reconocer nuestra clase, le agregamos la anotacion @RestController.
@Slf4j // Permite poder usar log.info para debuguear la aplicacion
public class ControladorInicio {

    // Permite acceder a variables del application.properties
    @Value("${index.saludo}")
    private String saludo;

    @GetMapping("/")
    public String inicio(Model model){
        // Variables
        var mensaje = "Hola Mundo con Thymeleaf";
        var persona = new Persona();
        var persona2 = new Persona();

        // Definir el objeto persona
        persona.setNombre("Carlos");
        persona.setApellido("Silvestri");
        persona.setEmail("carlossilvestri@gmail.com");
        persona.setTelefono("41412345");

        // Definir el objeto persona2
        persona2.setNombre("Karla");
        persona2.setApellido("Gomez");
        persona2.setEmail("kgomez@gmail.com");
        persona2.setTelefono("41412345");

        // Definir un arreglo de personas
        /*
        // Forma de declarar un array de objetos
        var personas = new ArrayList<Persona>();
        personas.add(persona);
        personas.add(persona2);
        */
        // Otra forma tambien de declarar un arreglo.
        var personas = Arrays.asList(persona, persona2);
        // var personas = new ArrayList<>();


        // LOGS en la consola
        log.info("Ejecutando el controlador Spring MVC");
        log.debug("mas detalles del controlador");

        // Pasarle las variables a thymeleaf
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
        model.addAttribute("persona", persona);
        model.addAttribute("persona2", persona2);
        model.addAttribute("personas", personas);

        // Nombre del archivo html que utiliza tymeleaf como template engine.
        return "index";
    }
}
