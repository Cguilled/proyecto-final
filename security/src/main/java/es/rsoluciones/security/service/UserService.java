package es.rsoluciones.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.rsoluciones.security.model.User;
import es.rsoluciones.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User loadUserByUsername(String username)  {
		return userRepository.findByUsername(username);
	}
	
	public User save(User user) {
		//codificar password
		user.setEnbled(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@PreAuthorize("hasAuthority('USER')") //no puede, user viene vacio de grantedauthorities
	public String permitir(User user) {
		return "CORRECTO";
	}
	
	@PreAuthorize("hasPermission(authentication, 'USER')") //no puede, user viene vacio de grantedauthorities
	public String permitir2(User user) {
		return "CORRECTO";
	}

}
