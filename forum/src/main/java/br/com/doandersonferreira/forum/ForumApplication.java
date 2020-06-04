package br.com.doandersonferreira.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport //  Dentre outras coisas, permite a injecao de uma instancia Pageable automaticamente em metodos do controller a partir de request parameters.
@EnableCaching // Habilita o uso de cache na aplicacao
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
