package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import pe.edu.upc.model.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Integer> {

	public Role findByName(String role);

}
