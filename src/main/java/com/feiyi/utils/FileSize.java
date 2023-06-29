package com.feiyi.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {

    String message() default "Upload file size exceeds the limit: {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String max();
}
