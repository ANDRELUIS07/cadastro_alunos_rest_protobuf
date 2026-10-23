# Comparação JSON × Protobuf

Objeto usado: `Aluno(id=1, nome="Ana Silva", curso="Engenharia de Software")`

| Critério | JSON | Protobuf |
|---|---|---|
| Representação | Texto | Binário |
| Legibilidade | Alta, legível por humanos | Baixa, exige ferramenta para ler |
| Tamanho (este exemplo) | 60 bytes | 37 bytes (confirmar na saída do `ProtobufDemo`) |
| Dependência de esquema | Opcional (JSON Schema) | Obrigatória (arquivo `.proto`) |
| Nome dos campos na mensagem | Enviados como texto | Não enviados, só o número do campo |
| Geração de código | Não necessária | Classes geradas a partir do `.proto` |
| Uso típico | APIs REST públicas | gRPC e comunicação interna entre serviços |

## Quando usar cada formato em uma API REST

- **JSON é preferível:** APIs públicas, consumo por navegador ou front-end, depuração simples com curl/Postman, equipes ou clientes variados sem acesso ao `.proto`.
- **Protobuf é preferível:** comunicação interna entre microsserviços, grande volume de mensagens, necessidade de economizar banda e desempenho, contratos rígidos e versionados (como no gRPC).

## Onde ocorrem serialização e desserialização

- No REST: o Spring (Jackson) converte `Aluno` em JSON na resposta e JSON em `Aluno` na requisição.
- No Protobuf: `toByteArray()` serializa e `parseFrom()` desserializa.
