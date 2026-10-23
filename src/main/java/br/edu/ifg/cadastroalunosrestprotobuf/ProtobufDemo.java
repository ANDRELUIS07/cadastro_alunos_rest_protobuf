package br.edu.ifg.cadastroalunosrestprotobuf;

import br.edu.ifg.cadastroalunosrestprotobuf.proto.AlunoProto;
import tools.jackson.databind.json.JsonMapper;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class ProtobufDemo {

    public static void main(String[] args) throws Exception {
        Aluno aluno = new Aluno(1, "Ana Silva", "Engenharia de Software");

        // JSON
        String json = JsonMapper.builder().build().writeValueAsString(aluno);
        int bytesJson = json.getBytes(StandardCharsets.UTF_8).length;

        // Protobuf: serializar
        AlunoProto.Aluno proto = AlunoProto.Aluno.newBuilder()
                .setId(aluno.id())
                .setNome(aluno.nome())
                .setCurso(aluno.curso())
                .build();
        byte[] binario = proto.toByteArray();

        // Protobuf: desserializar
        AlunoProto.Aluno lido = AlunoProto.Aluno.parseFrom(binario);

        System.out.println("JSON:     " + json);
        System.out.println("Protobuf: " + HexFormat.of().formatHex(binario));
        System.out.println("Objeto Protobuf lido: " + lido.getNome() + " / " + lido.getCurso());
        System.out.println();
        System.out.println("Bytes JSON:     " + bytesJson);
        System.out.println("Bytes Protobuf: " + binario.length);
        System.out.println("Protobuf e " + (100 - binario.length * 100 / bytesJson) + "% menor");
    }
}
