package com.javierlobo.clientes.logic;

import com.javierlobo.clientes.persistence.entity.UsuarioEntity;

public interface IUsuarioService {

	public UsuarioEntity findByUsername(String username);

}
