package com.javierlobo.clientes.logic.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javierlobo.clientes.logic.IUsuarioService;
import com.javierlobo.clientes.persistence.entity.UsuarioEntity;
import com.javierlobo.clientes.persistence.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioEntity usuario = usuarioRepository.findByUsername(username);

		if (usuario == null) {
			String msg = "Error en el login: no existe el usuario " + username + "en el sistema!";
			logger.error(msg);
			throw new UsernameNotFoundException(msg);
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioEntity findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
