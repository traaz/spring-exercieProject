package com.example.proje;

import com.example.proje.core.exceptions.BusinessException;
import com.example.proje.core.exceptions.DataIntegrityViolationException;
import com.example.proje.core.results.ErrorResult;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestControllerAdvice
public class ProjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjeApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResult handleBusinessException(BusinessException businessException){
		return new ErrorResult(businessException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResult handleDataIntegrity(DataIntegrityViolationException dataIntegrityVioalationException) {
		return new ErrorResult(dataIntegrityVioalationException.getMessage());

	}


}
