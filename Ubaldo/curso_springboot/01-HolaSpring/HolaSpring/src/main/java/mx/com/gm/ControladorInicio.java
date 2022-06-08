package mx.com.gm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Para que spring pueda reconocer nuestra clase, le agregamos la anotacion @RestController.
@Slf4j // Permite poder usar log.info para debuguear la aplicacion
public class ControladorInicio {

    @GetMapping("/")
    public String inicio(){
        log.info("Ejecutando el controlador rest");
        log.debug("mas detalles del controlador");
        return "HolaMundo con Spring 2";
    }
}
