package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.config.ChangePasswordForm;
import pe.edu.upc.model.Administrador;


public interface IAdministradorService {
	public boolean insertar(Administrador administrador);
	public boolean modificar(Administrador administrador);
	public void eliminar(int idAdministrador);
	public Optional<Administrador> listarId(int idAdministrador);
	
	List<Administrador> findByName(String nombreAdministrador);
	/*List<Administrador> findByDocument(String documentoAdministrador);*/
	
	
	
	
	Optional<Administrador> buscarNombre(String nombreAdministrador);
	//Administrador createUser(Administrador administrador) throws Exception;
	
	Administrador getAdministradorById(int idAdministradore)throws Exception;
	public Administrador updateUser(Administrador administrador) throws Exception;
	//public void deleteUser(int id) throws Exception;
	

	public Iterable<Administrador> listar();
	
	//public Administrador changePassword(ChangePasswordForm form) throws Exception;
	
	
	
}
