package com.algaworks.algafood.core.validation;

import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private DataSize dataSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        this.dataSize = DataSize.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        /*O tamanho do arquivo da requisição é em Bytes e datasize quarda o valor limite especificado na anotação */
        return file == null || file.getSize() <= dataSize.toBytes();
    }
}
