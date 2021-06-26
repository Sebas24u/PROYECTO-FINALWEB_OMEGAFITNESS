package pe.edu.upc.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.text.ParseException;

import pe.edu.upc.model.Entrenador;
import pe.edu.upc.service.IEntrenadorService;

@Controller

public class IndexController {

	@Autowired
	private IEntrenadorService rService;
	
	

	
	@GetMapping("/index")	
	public String ListaEntrenadores() {
		return "entrenador/entrenador";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
}
