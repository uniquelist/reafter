package com.feiyi.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

    private long maxSize;

    @Override
    public void initialize(FileSize fileSize) {
        String value = fileSize.max().toUpperCase();
        Matcher matcher = Pattern.compile("(\\d+)(KB|MB|GB)").matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid file size format: " + value);
        }
        long size = Long.parseLong(matcher.group(1));

        String unit = matcher.group(2);
        switch (unit) {
            case "KB":
                maxSize = size * 1024;
                break;
            case "MB":
                maxSize = size * 1024 * 1024;
                break;
            case "GB":
                maxSize = size * 1024 * 1024 * 1024;
                break;
            default:
                throw new IllegalArgumentException("Invalid file size format: " + value);
        }
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile == null) {
            return true;
        }
        return multipartFile.getSize() <= maxSize;
    }
}
