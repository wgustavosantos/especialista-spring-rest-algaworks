package com.algaworks.algafood.core.validation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    String[] allowed;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowed = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile file,
                           ConstraintValidatorContext constraintValidatorContext) {

        ArrayList<String> allowedContentTypes = new ArrayList<>(Arrays.asList(allowed));

        return allowedContentTypes.contains(file.getContentType());
    }
}
