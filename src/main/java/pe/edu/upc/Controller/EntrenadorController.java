package pe.edu.upc.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.Exception.CustomeFieldValidationException;
import pe.edu.upc.model.Entrenador;
import pe.edu.upc.service.IEntrenadorService;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {
	
	@Autowired
	private IEntrenadorService rService;
	
/*	@RequestMapping("/bienvenido")
	public String irEntrenadorBienvenida() {
		return "bienvenido";
	}
	*/
	
	@RequestMapping("/")
	public String irPaginaListadoEntrenadores( Model model) {
		model.addAttribute("entrenador", new Entrenador());
		model.addAttribute("listaEntrenadores", rService.listar());
		return "entrenador/listEntrenador";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("entrenador", new Entrenador());
		return "entrenador/entrenador";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Entrenador objEntrenador, BindingResult binRes, Model model) throws Exception {
		if (binRes.hasErrors())
			return "entrenador/entrenador";
		else {
			boolean flag = rService.insertar(objEntrenador);
			if (flag)
				return "redirect:/entrenador/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/entrenador/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws Exception {
		Optional<Entrenador> objEntrenador = rService.listarId(id);
		if(objEntrenador == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/entrenador/listar";
		}
		else {
			model.addAttribute("entrenador", objEntrenador);
			return "entrenador/entrenador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				rService.eliminar(id);
				model.put("entrenador", new Entrenador());
				model.put("listaEntrenadores", rService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("entrenador", new Entrenador());
			model.put("listaEntrenadores", rService.listar());
		}
		return "entrenador/listEntrenador";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("entrenador", new Entrenador());
		model.addAttribute("listaEntrenadores", rService.listar());
		return "entrenador/listEntrenador";
	}
	
	
	
	
}
