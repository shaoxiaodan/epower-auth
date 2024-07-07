package edu.nau.epower_auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("edu.nau.epower_auth.mapper")
public class EpowerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpowerAuthApplication.class, args);
	}

}
