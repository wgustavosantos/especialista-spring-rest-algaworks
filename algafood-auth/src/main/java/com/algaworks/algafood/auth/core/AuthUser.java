package com.algaworks.algafood.auth.core;

import com.algaworks.algafood.auth.domain.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

@Getter
public class AuthUser extends User {
    @Serial
    private static final long serialVersionUID = -2094010971499823455L;
    private String fullName;
    private Long userId;

    public AuthUser (Usuario usuario){
        super(usuario.getEmail(), usuario.getSenha(), Collections.emptyList());

        fullName = usuario.getEmail();
        userId = usuario.getId();
    }

}
