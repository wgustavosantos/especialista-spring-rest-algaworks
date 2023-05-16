package com.algaworks.algafood.auth.core;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    /**
     *  customiza o token antes de ser emitido
     *
     * @param oAuth2AccessToken
     * representa o token e pode customizar o token antes de ser emitido
     * @param oAuth2Authentication
     * representa a autenticação do usuário, dentro contém o User
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        if(oAuth2Authentication.getPrincipal() instanceof AuthUser){
            final AuthUser authUser = (AuthUser) oAuth2Authentication.getPrincipal();

            final HashMap<String, Object> info = new HashMap<>();

            info.put("nome_completo",authUser.getFullName());
            info.put("usuario_id", authUser.getUserId());

            oAuth2AccessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        }

        return oAuth2AccessToken;
    }
}
