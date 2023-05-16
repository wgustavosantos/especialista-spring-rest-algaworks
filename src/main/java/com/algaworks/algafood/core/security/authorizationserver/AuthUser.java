package com.algaworks.algafood.core.security.authorizationserver;

import com.algaworks.algafood.domain.model.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serial;
import java.util.Collection;

@Getter
public class AuthUser extends User {
    @Serial
    private static final long serialVersionUID = -2094010971499823455L;
    private String fullName;
    private Long userId;

    public AuthUser (Usuario usuario, Collection<? extends GrantedAuthority> authorities){
        super(usuario.getEmail(), usuario.getSenha(), authorities);

        fullName = usuario.getEmail();
        userId = usuario.getId();
    }

}
