package com.algaworks.algafood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
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
}
