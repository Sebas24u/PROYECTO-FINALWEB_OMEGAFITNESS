package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.Exception.CustomeFieldValidationException;
import pe.edu.upc.config.ChangePasswordForm;
import pe.edu.upc.model.Administrador;

import pe.edu.upc.repository.IAdministradorRepository;
import pe.edu.upc.service.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

	@Autowired
	private IAdministradorRepository adminR;
	
/*	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;*/
	
	//----------------------------------------------
	
	@Override
	@Transactional
	public boolean insertar(Administrador administrador) {
		Administrador objAdministrador = adminR.save(administrador);
		if(objAdministrador == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional
	public boolean modificar(Administrador administrador) {
		boolean flag = false;
		try {
			adminR.save(administrador);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un problema");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idAdministrador) {
		adminR.deleteById(idAdministrador);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Administrador> listarId(int idAdministrador) {
		return adminR.findById(idAdministrador);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Administrador> listar() {
		return adminR.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Administrador> findByName(String nombreAdministrador) {
		List<Administrador> lista = adminR.findByName(nombreAdministrador);
		return lista;
	}

	//------------------------------------------
	
	private boolean checkUsernameAvailable(Administrador administrador) throws Exception {
		Optional<Administrador> userFound = adminR.findByUsername(administrador.getUsername());
		if (userFound.isPresent()) {
			throw new CustomeFieldValidationException("Username no disponible","username");
		}
		return true;
	}

	
	public boolean checkPasswordValid(Administrador administrador) throws Exception {
		
		if (administrador.getConfirmarpasword() == null || administrador.getConfirmarpasword().isEmpty()) {
			throw  new CustomeFieldValidationException("Confirmar pasword es obligatorio","confirmarpasword");
		}

		
		
		if ( !administrador.getPasword().equals(administrador.getConfirmarpasword())) {
			throw new CustomeFieldValidationException("Pasword y Confirmar pasword es no son iguales","pasword");
		}
		return true;
	}


/*
	@Override
	public Administrador createUser(Administrador administrador) throws Exception {
				
		if (checkUsernameAvailable(administrador) && checkPasswordValid(administrador)) {
			
			
			String encodePassword= bCryptPasswordEncoder.encode(administrador.getPasword());
			administrador.setPasword(encodePassword);
			administrador = adminR.save(administrador);
		}
		return administrador;
	}*/

	@Override
	public Administrador getAdministradorById(int idAdministrador)throws Exception {
		return adminR.findById(idAdministrador).orElseThrow(() -> new Exception("El usuario para editar no existe"));
	}
	
	@Override
	public Administrador updateUser(Administrador fromUser) throws Exception {
		Administrador toUser = getAdministradorById(fromUser.getIdAdministrador());
		mapUser(fromUser, toUser);
		return adminR.save(toUser);
	}
	
	

	
	  protected void mapUser(Administrador from,Administrador to) {
		to.setUsername(from.getUsername());
		to.setNombreAdministrador(from.getNombreAdministrador());
		to.setApellidoAdministrador(from.getApellidoAdministrador());
		to.setDocumentoAdministrador(from.getDocumentoAdministrador());
		to.setDireccionAdministrador(from.getDireccionAdministrador());
		to.setTelefonoAdministrador(from.getTelefonoAdministrador());
		to.setCorreoAdministrador(from.getCorreoAdministrador());
		to.setRoles(from.getRoles());
	}


	@Override
	public Optional<Administrador> buscarNombre(String nombreAdministrador) {
		// TODO Auto-generated method stub
		return null;
	}



/*	  
	  @Override
	  @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	  public void deleteUser(int id) throws Exception {
		  Administrador user = adminR.findById(id)
					.orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

			adminR.delete(user);
		}
	  
	  @Override
	  public Administrador changePassword(ChangePasswordForm form) throws Exception{
		  Administrador storedUser = getAdministradorById(form.getId());
					
			
			if (!isLoggedUserADMIN() && !storedUser.getPasword().equals(form.getCurrentPassword())) {
				throw new Exception ("Current Password invalido.");
			}
			
			if ( storedUser.getPasword().equals(form.getNewPassword())) {
				throw new Exception("New Password must be different than Current Password!");
			}
			
			if( !form.getNewPassword().equals(form.getConfirmPassword())) {
				throw new Exception("New Password and Confirm Password does not match!");
			}
			
			String encodePassword= bCryptPasswordEncoder.encode(form.getNewPassword());
			storedUser.setPasword(encodePassword);
			return adminR.save(storedUser);
		}
	  
*/	  
	  
/*	  public boolean isLoggedUserADMIN(){
		  return loggedUserHasRole("ROLE_ADMIN");
		 }

		 public boolean loggedUserHasRole(String role) {
		  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  UserDetails loggedUser = null;
		  Object roles = null; 
		  if (principal instanceof UserDetails) {
		   loggedUser = (UserDetails) principal;
		  
		   roles = loggedUser.getAuthorities().stream()
		     .filter(x -> role.equals(x.getAuthority() ))      
		     .findFirst().orElse(null); //loggedUser = null;
		  }
		  return roles != null ?true :false;
		 }


		@Override
		public Optional<Administrador> buscarNombre(String nombreAdministrador) {
			// TODO Auto-generated method stub
			return null;
		}
	  
*/
}
