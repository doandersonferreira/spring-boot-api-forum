package br.com.doandersonferreira.forum.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.doandersonferreira.forum.controller.dto.TopicoDto;
import br.com.doandersonferreira.forum.controller.form.TopicoForm;
import br.com.doandersonferreira.forum.model.Topico;
import br.com.doandersonferreira.forum.repository.CursoRepository;
import br.com.doandersonferreira.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	// Injeção de dependêcia
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	
	@GetMapping
	public List<TopicoDto> lista(String nomeCurso){		

		if(nomeCurso == null) {
			// Obtem lista de tópico do banco de dados
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);	
		}else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDto.converter(topicos);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		// Implementando o retorno do código HTTP (201 - Created) e o recurso
		// no corpo
		URI uri = uriBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
}
