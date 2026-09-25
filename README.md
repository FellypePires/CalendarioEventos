# CalendarioEventos

Projeto simples de faculdade feito com Java, Spring Boot, Spring Web e Maven.
Os eventos ficam armazenados em uma `ArrayList` dentro do Controller.

## Executar

No Windows, abra um terminal dentro da pasta do projeto e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Rotas

| Método | Rota | Descrição |
|---|---|---|
| GET | `/evento/listar-todos` | Lista todos os eventos |
| GET | `/evento/{id}` | Busca um evento pelo ID |
| POST | `/evento/gravar` | Cadastra um evento |
| PUT | `/evento/editar/{id}` | Atualiza um evento |
| DELETE | `/evento/deletar/{id}` | Exclui um evento |
| GET | `/evento/filtrar` | Filtra por título, data e categoria |

## Exemplo de JSON

```json
{
  "titulo": "Prova de Java",
  "descricao": "Avaliação da disciplina de Desenvolvimento Web",
  "data": "2026-10-05",
  "horario": "19:30",
  "categoria": "Faculdade"
}
```

O ID é gerado automaticamente e não deve ser informado no cadastro.

## Exemplos de filtros

```text
GET /evento/filtrar?titulo=prova
GET /evento/filtrar?data=2026-10-05
GET /evento/filtrar?categoria=Faculdade
GET /evento/filtrar?data=2026-10-05&categoria=Faculdade
GET /evento/filtrar?titulo=prova&data=2026-10-05&categoria=Faculdade
```
