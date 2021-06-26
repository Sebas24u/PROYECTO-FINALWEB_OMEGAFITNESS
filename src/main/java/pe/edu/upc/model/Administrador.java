package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="Administrador")
public class Administrador implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -191695689954210325L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdministrador;
	
	@Column(name="nombreAdministrador", nullable = false, length=30)
		
	private String nombreAdministrador;
	
	@Column(name = "apellidoAdministrador", nullable = false, length=30)
	
	private String apellidoAdministrador;
	
	@Column(name = "documentoAdministrador", nullable = false, length=30)

	private String documentoAdministrador;
	
	@Column(name = "direccionAdministrador", nullable = false, length=50)
	private String direccionAdministrador;
	
	@Column(name = "telefonoAdministrador", nullable = false, length=30)
	private String telefonoAdministrador;
	
	@Column(name = "correoAdministrador", nullable = false, length=50)
	private String correoAdministrador;
	
	@Column(name = "username",nullable = false, length=30)
	@NotBlank
	private String username;
	
	@Column(name = "pasword", length=100)
	private String pasword;
	
	@Transient
	private String confirmarpasword;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name= "user_roles",
				joinColumns=@JoinColumn(name= "user_id"),
				inverseJoinColumns=@JoinColumn(name= "role_id"))
	private Set<Role> roles;
	
	
	public Administrador() {
		super();
	}


	public Administrador(int idAdministrador, String nombreAdministrador, String apellidoAdministrador,
			String documentoAdministrador, String direccionAdministrador, String telefonoAdministrador,
			String correoAdministrador, @NotBlank String username, String pasword, String confirmarpasword,
			Set<Role> roles) {
		super();
		this.idAdministrador = idAdministrador;
		this.nombreAdministrador = nombreAdministrador;
		this.apellidoAdministrador = apellidoAdministrador;
		this.documentoAdministrador = documentoAdministrador;
		this.direccionAdministrador = direccionAdministrador;
		this.telefonoAdministrador = telefonoAdministrador;
		this.correoAdministrador = correoAdministrador;
		this.username = username;
		this.pasword = pasword;
		this.confirmarpasword = confirmarpasword;
		this.roles = roles;
	}


	public int getIdAdministrador() {
		return idAdministrador;
	}


	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}


	public String getNombreAdministrador() {
		return nombreAdministrador;
	}


	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}


	public String getApellidoAdministrador() {
		return apellidoAdministrador;
	}


	public void setApellidoAdministrador(String apellidoAdministrador) {
		this.apellidoAdministrador = apellidoAdministrador;
	}


	public String getDocumentoAdministrador() {
		return documentoAdministrador;
	}


	public void setDocumentoAdministrador(String documentoAdministrador) {
		this.documentoAdministrador = documentoAdministrador;
	}


	public String getDireccionAdministrador() {
		return direccionAdministrador;
	}


	public void setDireccionAdministrador(String direccionAdministrador) {
		this.direccionAdministrador = direccionAdministrador;
	}


	public String getTelefonoAdministrador() {
		return telefonoAdministrador;
	}


	public void setTelefonoAdministrador(String telefonoAdministrador) {
		this.telefonoAdministrador = telefonoAdministrador;
	}


	public String getCorreoAdministrador() {
		return correoAdministrador;
	}


	public void setCorreoAdministrador(String correoAdministrador) {
		this.correoAdministrador = correoAdministrador;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPasword() {
		return pasword;
	}


	public void setPasword(String pasword) {
		this.pasword = pasword;
	}


	public String getConfirmarpasword() {
		return confirmarpasword;
	}


	public void setConfirmarpasword(String confirmarpasword) {
		this.confirmarpasword = confirmarpasword;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidoAdministrador == null) ? 0 : apellidoAdministrador.hashCode());
		result = prime * result + ((confirmarpasword == null) ? 0 : confirmarpasword.hashCode());
		result = prime * result + ((correoAdministrador == null) ? 0 : correoAdministrador.hashCode());
		result = prime * result + ((direccionAdministrador == null) ? 0 : direccionAdministrador.hashCode());
		result = prime * result + ((documentoAdministrador == null) ? 0 : documentoAdministrador.hashCode());
		result = prime * result + idAdministrador;
		result = prime * result + ((nombreAdministrador == null) ? 0 : nombreAdministrador.hashCode());
		result = prime * result + ((pasword == null) ? 0 : pasword.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((telefonoAdministrador == null) ? 0 : telefonoAdministrador.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrador other = (Administrador) obj;
		if (apellidoAdministrador == null) {
			if (other.apellidoAdministrador != null)
				return false;
		} else if (!apellidoAdministrador.equals(other.apellidoAdministrador))
			return false;
		if (confirmarpasword == null) {
			if (other.confirmarpasword != null)
				return false;
		} else if (!confirmarpasword.equals(other.confirmarpasword))
			return false;
		if (correoAdministrador == null) {
			if (other.correoAdministrador != null)
				return false;
		} else if (!correoAdministrador.equals(other.correoAdministrador))
			return false;
		if (direccionAdministrador == null) {
			if (other.direccionAdministrador != null)
				return false;
		} else if (!direccionAdministrador.equals(other.direccionAdministrador))
			return false;
		if (documentoAdministrador == null) {
			if (other.documentoAdministrador != null)
				return false;
		} else if (!documentoAdministrador.equals(other.documentoAdministrador))
			return false;
		if (idAdministrador != other.idAdministrador)
			return false;
		if (nombreAdministrador == null) {
			if (other.nombreAdministrador != null)
				return false;
		} else if (!nombreAdministrador.equals(other.nombreAdministrador))
			return false;
		if (pasword == null) {
			if (other.pasword != null)
				return false;
		} else if (!pasword.equals(other.pasword))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (telefonoAdministrador == null) {
			if (other.telefonoAdministrador != null)
				return false;
		} else if (!telefonoAdministrador.equals(other.telefonoAdministrador))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Administrador [idAdministrador=" + idAdministrador + ", nombreAdministrador=" + nombreAdministrador
				+ ", apellidoAdministrador=" + apellidoAdministrador + ", documentoAdministrador="
				+ documentoAdministrador + ", direccionAdministrador=" + direccionAdministrador
				+ ", telefonoAdministrador=" + telefonoAdministrador + ", correoAdministrador=" + correoAdministrador
				+ ", username=" + username + ", pasword=" + pasword + ", confirmarpasword=" + confirmarpasword
				+ ", roles=" + roles + "]";
	}


	

	
}
	

	