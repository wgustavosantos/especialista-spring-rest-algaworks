package com.algaworks.algafood.core.storage;

import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.LocalFotoStorageService;
import com.algaworks.algafood.infrastructure.service.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    @ConditionalOnProperty(name = "algafood.storage.tipo-armazenamento", havingValue = "amazons3")
    public AmazonS3 amazonS3(){

        final String idChaveAcesso = storageProperties.getS3().getIdChaveAcesso();
        final String chaveAcessoSecreta = storageProperties.getS3().getChaveAcessoSecreta();
        Regions regiao = storageProperties.getS3().getRegiao();

        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(idChaveAcesso, chaveAcessoSecreta);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).
                withRegion(regiao).
                build();
    }

    @Bean
    public FotoStorageService fotoStorageService (){

        if(storageProperties.getTipoArmazenamento().equals(StorageProperties.TipoArmazenamento.LOCALSTORAGE)){
            return new LocalFotoStorageService();
        } else
            return new S3FotoStorageService();
    }

}
