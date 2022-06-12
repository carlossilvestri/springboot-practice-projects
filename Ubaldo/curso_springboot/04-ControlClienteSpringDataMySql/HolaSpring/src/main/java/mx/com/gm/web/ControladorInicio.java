package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.domain.Persona;
import mx.com.gm.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Para que spring pueda reconocer nuestra clase, le agregamos la anotacion @RestController.
@Slf4j // Permite poder usar log.info para debuguear la aplicacion
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model){
        var personas = personaService.listarPersonas();
        // LOGS en la consola
        // log.info("Ejecutando el controlador Spring MVC");
        // System.out.println("personas " + personas);
        model.addAttribute("personas", personas);

        // Nombre del archivo html que utiliza tymeleaf como template engine.
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona){

        return "modificar"; // Nombre del archivo html que utiliza tymeleaf como template engine.
    }

    @PostMapping("/guardar")
    public String guardar(Persona persona){
        personaService.guardar(persona);
        return "redirect:/"; //Redirecciona a la pagina de inicio "/"
    }

    @GetMapping("/editar/{idPersona}") // Al colocar {idPersona} Spring automaticamente coloca el metodo setIdPersona en la variable persona
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona); // El model lo usamos para enviar a la variable de persona a la vista.
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/"; //Redirecciona a la pagina de inicio "/"
    }

    /*
    Ejemplo:
    /eliminar?idPersona=1
    Spring automaticamente actualiza con el metodo set la variable persona dependiendo de la variable a pasar por query params.
    */
    @GetMapping("/eliminar-persona")
    public String eliminarConQueryParams(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/"; //Redirecciona a la pagina de inicio "/"
    }
}
