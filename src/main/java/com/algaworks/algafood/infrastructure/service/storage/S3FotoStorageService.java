package com.algaworks.algafood.infrastructure.service.storage;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.net.URL;

public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void armazenar(NovaFoto novaFoto) {
        final String bucket = storageProperties.getS3().getBucket();
        final String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
        final InputStream inputStream = novaFoto.getInputStream();
        final String contentType = novaFoto.getContentType();
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        final CannedAccessControlList publicRead = CannedAccessControlList.PublicRead;

        final PutObjectRequest putObjectRequest = new PutObjectRequest
                (bucket, caminhoArquivo, inputStream, objectMetadata);

        putObjectRequest.withCannedAcl(publicRead);

        try {
            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
        }
    }

    private String getCaminhoArquivo(String nomeArquivo) {
        /*catalogo/nome_da_foto.jpg*/
        return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
    }

    @Override
    public void remover(String nomeArquivo) {
        String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
        String bucket = storageProperties.getS3().getBucket();
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucket, caminhoArquivo);
        try {
            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }

    }

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        final String bucket = storageProperties.getS3().getBucket();
        final String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
        final URL url = amazonS3.getUrl(bucket, caminhoArquivo);

        return FotoRecuperada.builder().urlFoto(url.toString()).build();
    }
}
