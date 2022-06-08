package mx.com.gm.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/*
* @Data, es una anotacioin de la libreria lombok que permite crear codigo repetivo automaticamente.
* Por ejemplo crea los metodos set y get, toEquals, constructor vacio.
* */
@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
