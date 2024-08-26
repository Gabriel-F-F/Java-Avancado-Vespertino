package jv.triersistemas.primeiro_projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	private static List<Tarefa> listaTarefa = new ArrayList<>();
	private static AtomicLong contador = new AtomicLong();
	
	@GetMapping
	public List<Tarefa> getTarefa() {
		return listaTarefa;
	}
	
	@GetMapping("/{id}")
	public Tarefa getTarefaPorId(@PathVariable("id") Long id) {
		return listaTarefa.stream().filter(tarefa -> tarefa.getId().equals(id)).findFirst().orElse(null);
	}
	
	@PostMapping
	public void postTarefa(@RequestBody Tarefa tarefaRequest) {
		tarefaRequest.setId(contador.incrementAndGet());
		listaTarefa.add(tarefaRequest);
	}
	
	@PutMapping("/{id}")
	public void putTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefaRequest) {
		Optional<Tarefa> tarefaUpdate = listaTarefa.stream().filter(tarefa -> tarefa.getId().equals(id)).findFirst();
		
		if(tarefaUpdate.isPresent()) {
			Tarefa tarefa = tarefaUpdate.get();
			tarefa.setTitulo(tarefaUpdate.getTitulo());
			tarefa.setTitulo(tarefaUpdate.getDescricao());
			tarefa.setTitulo(tarefaUpdate.getCompleta());
		}
	}
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Tarefa {
	private Long id;
	private String titulo;
	private String descricao;
	private boolean completa;
}