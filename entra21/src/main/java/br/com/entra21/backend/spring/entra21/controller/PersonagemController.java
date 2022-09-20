package br.com.entra21.backend.spring.entra21.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.entra21.backend.spring.entra21.model.ItemNivel3;
import br.com.entra21.backend.spring.entra21.model.Personagem;
import br.com.entra21.backend.spring.entra21.repository.IPersonagemRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/personagens")
public class PersonagemController {

	@Autowired
	private IPersonagemRepository personagemRepository;

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<Personagem> listar() {
		
		List<Personagem> response = personagemRepository.findAll();
		response.forEach(personagem ->{
			setMaturidadeNivel3(personagem);
		});
		
		return response;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Personagem> buscar(@PathVariable("id") int param) {

		List<Personagem> response = personagemRepository.findById(param).stream().toList();

		return response;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Personagem adicionar(@RequestBody Personagem novoPersonagem) {

		return personagemRepository.save(novoPersonagem);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Optional<Personagem> atualizar(@PathVariable("id") int param,
			@RequestBody Personagem personagemNovosDados) {

		Personagem atual = personagemRepository.findById(param).get();
		atual.setNome_heroi(personagemNovosDados.getNome_heroi());
		atual.setNome_real(personagemNovosDados.getNome_real());
		atual.setHabilidade(personagemNovosDados.getHabilidade());
		atual.setAcessorio(personagemNovosDados.getAcessorio());
		atual.setIdade(personagemNovosDados.getIdade());
				
		personagemRepository.save(atual);

		return personagemRepository.findById(param);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean deletar(@PathVariable("id") int id) {
		personagemRepository.deleteById(id);

		return !personagemRepository.existsById(id);
	}

	private void setMaturidadeNivel3(Personagem personagem) {

		final String PATH = "localhost:8080/personagens";
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Accept : application/json");
		headers.add("Content-type : application/json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);

		try {
			Personagem clone = mapper.readValue(mapper.writeValueAsString(personagem), Personagem.class);

			clone.setLinks(null);

			String nome_heroi = clone.getNome_heroi();
			String habilidade = clone.getHabilidade();
			String nome_real = clone.getNome_real();
			boolean acessorio = true;
			Integer idade = clone.getIdade();
			
			clone.setNome_heroi("Nome diferente");
			clone.setHabilidade("Habilidade Diferente");
			clone.setNome_real("Nome Real Diferente");
			clone.setAcessorio(true);
			clone.setIdade(1);
			
			String jsonUpdate = mapper.writeValueAsString(clone);

			clone.setNome_heroi(nome_heroi);
			clone.setHabilidade(habilidade);
			clone.setNome_real(nome_real);
			clone.setAcessorio(acessorio);
			clone.setIdade(idade);
			
			clone.setId(null);

			String jsonCreate = mapper.writeValueAsString(clone);
			personagem.setLinks(new ArrayList<>());
			personagem.getLinks().add(new ItemNivel3("GET", PATH, null, null));
			personagem.getLinks().add(new ItemNivel3("GET", PATH + "/" + personagem.getId(), null, null));
			personagem.getLinks().add(new ItemNivel3("POST", PATH, headers, jsonCreate));
			personagem.getLinks().add(new ItemNivel3("PUT", PATH + "/" + personagem.getId(), headers, jsonUpdate));

		} catch (JsonProcessingException e) {
			e.printStackTrace();

		}

	}

}
