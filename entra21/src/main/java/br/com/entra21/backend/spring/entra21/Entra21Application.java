package br.com.entra21.backend.spring.entra21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Entra21Application implements CommandLineRunner{
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public static void main(String[] args) {
		SpringApplication.run(Entra21Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//	String sql = "INSERT INTO programador (nome, qtd_linguagem) VALUES (?,?)";
	//	int result = jdbc.update(sql, "Thiago", 1);
				
		String sql = "INSERT INTO personagem (nome_heroi, nome_real, habilidade, acessorio, idade) VALUES (?,?,?,?,?)";
		int result = jdbc.update(sql, "Super", "Heroi", "Super", true, 160);
	}

}
