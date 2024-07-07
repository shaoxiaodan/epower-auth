package edu.nau.epower_auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序入口
 * 
 * @ClassName: EpowerAuthApplication
 * @Description: TODO
 * @author Xiaodan Shao(xs94@nau.edu)
 * @date 2024-07-06 10:17:55
 */
@SpringBootApplication
@MapperScan("edu.nau.epower_auth.mapper")
public class EpowerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpowerAuthApplication.class, args);
	}

}
