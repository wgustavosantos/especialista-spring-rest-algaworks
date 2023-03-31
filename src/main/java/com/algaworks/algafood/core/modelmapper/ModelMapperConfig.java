package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.dto.EnderecoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.ItemPedidoInputDTO;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();
        //11.16. Customizando o mapeamento de propriedades com ModelMapper
//        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

        TypeMap<Endereco, EnderecoDTO> typeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
        typeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value));

//        final TypeMap<ItemPedidoInputDTO, ItemPedido> typeMap1 = modelMapper.createTypeMap(ItemPedidoInputDTO.class, ItemPedido.class);
//                typeMap1.<Long>addMapping(ItemPedidoInputDTO::getId,
//                        ItemPedido::setId);
        modelMapper.typeMap(ItemPedidoInputDTO.class, ItemPedido.class)
                .addMappings(mapping -> mapping.skip(ItemPedidoInputDTO::getProdutoId, ItemPedido::setId));
        return modelMapper;
    }
}