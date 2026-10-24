# Comparação JSON × Protobuf

Objeto usado: `Aluno(id=1, nome="Ana Silva", curso="Engenharia de Software")`

| Critério | JSON | Protobuf |
|---|---|---|
| Representação | Texto | Binário |
| Legibilidade | Alta, legível por humanos | Baixa, exige ferramenta para ler |
| Tamanho (este exemplo) | 60 bytes | 37 bytes (cerca de 39% menor) |
| Dependência de esquema | Opcional (JSON Schema) | Obrigatória (arquivo `.proto`) |
| Nome dos campos na mensagem | Enviados como texto | Não enviados, só o número do campo |
| Geração de código | Não necessária | Classes geradas a partir do `.proto` |
| Uso típico | APIs REST públicas | gRPC e comunicação interna entre serviços |

## Resultado medido

Saída do `ProtobufDemo` para o objeto acima:

```
Bytes JSON:     60
Bytes Protobuf: 37
Protobuf e 39% menor
```

O Protobuf ocupa menos espaço porque não envia o nome dos campos (`id`, `nome`, `curso`), apenas o número de cada um (1, 2 e 3) definido no `.proto`.

## Quando usar cada formato em uma API REST

- **JSON é preferível:** APIs públicas, consumo por navegador ou front-end, depuração simples com curl/Postman, equipes ou clientes variados sem acesso ao `.proto`.
- **Protobuf é preferível:** comunicação interna entre microsserviços, grande volume de mensagens, necessidade de economizar banda e desempenho, contratos rígidos e versionados (como no gRPC).

## Onde ocorrem serialização e desserialização

- No REST: o Spring (Jackson) converte `Aluno` em JSON na resposta e JSON em `Aluno` na requisição.
- No Protobuf: `toByteArray()` serializa e `parseFrom()` desserializa.