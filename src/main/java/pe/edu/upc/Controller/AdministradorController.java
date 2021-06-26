package pe.edu.upc.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.validation.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.Exception.CustomeFieldValidationException;
import pe.edu.upc.config.ChangePasswordForm;

import pe.edu.upc.model.Administrador;

import pe.edu.upc.model.Entrenador;
import pe.edu.upc.model.Role;
import pe.edu.upc.repository.IRoleRepository;
/*import pe.edu.upc.model.Cliente;
import pe.edu.upc.model.Plan;
import pe.edu.upc.model.TipoPlan;*/
import pe.edu.upc.service.IAdministradorService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private IAdministradorService aService;
	
	
	@Autowired 
	IRoleRepository rol;
	
	//---------------------------------------
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")	
	public String signup(Model model) {
		Role userRole= rol.findByName("ADMIN");
		 List<Role> roles=Arrays.asList(userRole);
		
		model.addAttribute("administrador",new Administrador());
		model.addAttribute("roles", roles);
		model.addAttribute("signup",true);
		
		return "administrador/listAdministrador";
	}
	
	@PostMapping("/signup")
	public String signupAction(@Validated @ModelAttribute("administrador")Administrador user, BindingResult result, ModelMap model) {
		Role userRole = rol.findByName("ADMIN");
		List<Role> roles = Arrays.asList(userRole);
		model.addAttribute("administrador", user);
		model.addAttribute("roles",roles);
		model.addAttribute("signup",true);

		if(result.hasErrors()) {
			return "administrador/listAdministrador";
		}else {
	/*		try {
		aService.createUser(user);
		} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
			}catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
			}             */
		}
		return login();
	}
	
	
	@GetMapping("/administrador")	
	public String userform(Model model) {
		
		model.addAttribute("administrador",new Administrador());
		model.addAttribute("listTab","active");
		model.addAttribute("roles", rol.findAll());
		model.addAttribute("userList",aService.listar());
		return "user-form/user-view";
	}
	
	@PostMapping("/administrador")
	public String postUserForm(@Validated @ModelAttribute("administrador")Administrador administrador, BindingResult result, ModelMap model) {
			if(result.hasErrors()) {
				model.addAttribute("administrador", administrador);
				model.addAttribute("formTab","active");
			}else {
	/*			try {//Aca tendras error porque este metodo no existe, pero lo crearemos en la siguiente seccion.
					aService.createUser(administrador);
					model.addAttribute("administrador", new Administrador());
					model.addAttribute("listTab","active");
				}
				catch (CustomeFieldValidationException cfve) {
					result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
					model.addAttribute("administrador", administrador);
					model.addAttribute("formTab","active");
					model.addAttribute("userList", aService.listar());
					model.addAttribute("roles",rol.findAll());
				}
				
				catch (Exception e) {
					model.addAttribute("formError",e.getMessage());
					model.addAttribute("administrador", administrador);
					model.addAttribute("formTab","active");
					model.addAttribute("userList", aService.listar());
					model.addAttribute("roles",rol.findAll());
				}    
				*/
			}

			
			
			model.addAttribute("userList",aService.listar());
			model.addAttribute("roles",rol.findAll());
			return "user-form/user-view";
		}
	//-------------------------------------------
	
	@RequestMapping("/")
	public String irPaginaListadoAdministradores(Model model) {
		model.addAttribute("administrador", new Administrador());
		model.addAttribute("listaAdministradores", aService.listar());
		return "listAdministrador";
	}
	
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "administrador";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Administrador objAdministrador, BindingResult binRes, Model model) throws Exception {
		if (binRes.hasErrors())
			return "administrador/administrador";
		else {
			boolean flag = aService.insertar(objAdministrador);
			if (flag)
				return "redirect:/administrador/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/administrador/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws Exception {
		Optional<Administrador> objAdministrador = aService.listarId(id);
		if(objAdministrador == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/administrador/listar";
		}
		else {
			model.addAttribute("administrador", objAdministrador);
			return "administrador/administrador";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				aService.eliminar(id);
				model.put("administrador", new Administrador());
				model.put("listaAdministradores", aService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("administrador", new Administrador());
			model.put("listaAdministradores", aService.listar());
		}
		return "administrador/listAdministrador";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("administrador", new Administrador());
		model.addAttribute("listaAdministradores", aService.listar());
		return "administrador/listAdministrador";
	}
	
	@RequestMapping("/irBuscar")
	public String irPaginaBuscar(Model model ) {

		
		model.addAttribute("administrador", new Administrador());

		
		return "administradorbuscar/administrador";
	}
	
	
	@RequestMapping("/administradorbuscar")
	public String findbyAdministrador(Map<String, Object> model,
			@ModelAttribute  Administrador admin ) throws Exception {
		List<Administrador> listaAdministradores;
		admin.setNombreAdministrador(admin.getNombreAdministrador());
		listaAdministradores = aService.findByName(admin.getNombreAdministrador());
		//por aca se le puede cambiar a model y addAtribute
		model.put("listaAdministradores",listaAdministradores);
		return "administradorbuscar";
	}
	
}
