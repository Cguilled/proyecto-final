package es.rsoluciones.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.rsoluciones.security.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public User findByUsername(String u); //para cambiar el Optional
}
