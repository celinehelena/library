# 📚 API de Biblioteca Digital

Este projeto é uma API REST desenvolvida com Spring Boot que simula o funcionamento de uma **biblioteca digital**. O sistema permite que usuários reservem livros, deixem avaliações, e que administradores gerenciem o acervo e monitorem o uso da plataforma.

## 🚀 Funcionalidades

- Cadastro e autenticação de usuários.
- Listagem, reserva e devolução de livros.
- Limite de até 5 reservas ativas por usuário.
- Avaliação e comentários de livros.
- Classificação dos livros por categorias.
- Monitoramento de reservas e devoluções (admin).
- Remoção automática de reservas ativas vencidas.

## 🧩 Entidades principais

- **Usuários:** leitores e administradores.
- **Livros:** título, autor, ISBN, quantidade.
- **Reservas:** data de início, prazo e status.
- **Avaliações:** nota e comentário por usuário.
- **Categorias:** classificação por gênero ou tema.

### 🔗 Relacionamentos

- `Usuário (1:N) Reservas`
- `Livro (1:N) Reservas`
- `Livro (1:N) Avaliações`
- `Categoria (1:N) Livros`

## 🛠️ Tecnologias utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL (ou MySQL)
- Maven
- Lombok
- Hibernate Validator

## 📦 Endpoints principais

| Método | Rota                       | Descrição                          |
|--------|----------------------------|------------------------------------|
| GET    | `/livros`                  | Lista todos os livros              |
| POST   | `/usuarios`                | Cadastra um novo usuário           |
| POST   | `/reservas`                | Realiza uma nova reserva           |
| DELETE | `/reservas/{id}`           | Cancela uma reserva                |
| GET    | `/usuarios/{id}/reservas`  | Lista reservas ativas do usuário   |
| POST   | `/avaliacoes`              | Adiciona uma avaliação a um livro  |

> ⚠️ Reservas com data de devolução vencida não são listadas como ativas.

## 🧪 Como rodar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repo.git
   cd nome-do-repo
2. Configure o banco de dados em application.properties:
    ```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha

3. Rode o projeto:
   ```bash
   ./mvnw spring-boot:run
  
4. Acesse: http://localhost:8080

## 👩‍💻Desenvolvido por:
- @celinehelena
- @MarinnaSouza
