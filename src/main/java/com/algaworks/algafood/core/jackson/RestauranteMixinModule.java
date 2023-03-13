package com.algaworks.algafood.core.jackson;

import com.algaworks.algafood.api.model.mixin.RestauranteMixin;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMixinModule extends SimpleModule {
    public RestauranteMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }
}
