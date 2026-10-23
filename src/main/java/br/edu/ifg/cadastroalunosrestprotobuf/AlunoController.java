package br.edu.ifg.cadastroalunosrestprotobuf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final Map<Integer, Aluno> alunos = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable int id) {
        Aluno aluno = alunos.get(id);
        if (aluno == null) {
            return ResponseEntity.notFound().build();   // 404
        }
        return ResponseEntity.ok(aluno);                // 200
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        alunos.put(aluno.id(), aluno);
        return ResponseEntity
                .created(URI.create("/alunos/" + aluno.id()))   // 201
                .body(aluno);
    }
}