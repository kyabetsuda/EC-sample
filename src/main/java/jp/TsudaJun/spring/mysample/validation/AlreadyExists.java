package jp.TsudaJun.spring.mysample.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import jp.TsudaJun.spring.mysample.validator.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AlreadyExistsValidator.class}) // Validatorの実装クラスを指定
@ReportAsSingleViolation
public @interface AlreadyExists {
	
	String message() default "すでに存在しています";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public static @interface List {
        AlreadyExists[] value();
    }

}
