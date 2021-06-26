package pe.edu.upc.serviceimpl;
/*
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.repository.IAdministradorRepository;

import pe.edu.upc.model.Administrador;
import pe.edu.upc.model.Role;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    IAdministradorRepository userRepository;

	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Administrador appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login Username Invalido."));

		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
		for (Role role: appUser.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
		}
		UserDetails user = (UserDetails) new User(username,appUser.getPasword(),grantList);

		return user;
	}

}*/
