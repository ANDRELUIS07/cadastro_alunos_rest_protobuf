package br.edu.ifg.cadastroalunosrestprotobuf;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.nio.charset.StandardCharsets;

public class JsonDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = JsonMapper.builder().build();
        Aluno aluno = new Aluno(1, "Ana Silva", "Engenharia de Software");

        // Serializar: objeto -> JSON
        String json = mapper.writeValueAsString(aluno);
        System.out.println("JSON: " + json);
        System.out.println("Bytes JSON: " + json.getBytes(StandardCharsets.UTF_8).length);

        // Desserializar: JSON -> objeto
        Aluno lido = mapper.readValue(json, Aluno.class);
        System.out.println("Objeto: " + lido);
    }
}