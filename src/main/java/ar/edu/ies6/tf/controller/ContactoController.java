package ar.edu.ies6.tf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactoController {

    @GetMapping("/contactos")
    public ModelAndView mostrarFormularioContacto() {
        return new ModelAndView("contactos");
    }

 
}
