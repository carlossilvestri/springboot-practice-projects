package mx.com.gm.domain;

import lombok.Data;

/*
* @Data, es una anotacioin de la libreria lombok que permite crear codigo repetivo automaticamente.
* Por ejemplo crea los metodos set y get, toEquals, constructor vacio.
* */
@Data
public class Persona {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
