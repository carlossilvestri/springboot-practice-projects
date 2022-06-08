package mx.com.gm.web;

import lombok.extern.slf4j.Slf4j;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller // Para que spring pueda reconocer nuestra clase, le agregamos la anotacion @RestController.
@Slf4j // Permite poder usar log.info para debuguear la aplicacion
public class ControladorInicio {

    @Autowired
    private PersonaDao personaDao;

    @GetMapping("/")
    public String inicio(Model model){
        var personas = personaDao.findAll();
        // LOGS en la consola
        log.info("Ejecutando el controlador Spring MVC");
        log.info("personas ", personas);
        model.addAttribute("personas", personas);

        // Nombre del archivo html que utiliza tymeleaf como template engine.
        return "index";
    }
}
