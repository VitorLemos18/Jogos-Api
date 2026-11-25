# 🎮 Aokrys GameStore API

> API RESTful desenvolvida com **Java 21** e **Spring Boot** para o ecossistema de catálogo de jogos, conectando títulos, estúdios desenvolvedores/publishers e categorias.

---

## 💡 A Ideia da API

A **Aokrys GameStore API** é o núcleo de backend para uma plataforma de catálogo e venda de jogos digitais. Diferente de lojas convencionais que exibem apenas listas soltas de produtos, a API foi desenhada para estruturar a descoberta de títulos através do **ecossistema de criadores e gêneros**:

- **🏢 Estúdios e Produtoras:** Centraliza informações de cada empresa (data de fundação, histórico, portfólio completo de jogos desenvolvidos ou publicados).
- **🏷️ Categorias e Gêneros:** Organização modular para classificação dos jogos (um título pode abranger múltiplos gêneros, como RPG, Ação, Aventura e Indie).
- **🎯 Catálogo de Jogos:** Gestão completa dos títulos com controle de preço, estoque, data de lançamento e vínculo obrigatório com o estúdio produtor.
- **🛡️ Auditoria e Soft Delete:** Todas as entidades herdam campos de auditoria (`create_at`, `update_at`, `create_by`, `update_by`) e suporte a exclusão lógica via `deleted_at`.
- **👤 Controle de Usuários e Permissões:** Perfis distintos (`ADMIN`, `USER`) com regras de segurança para proteger rotas administrativas.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Descrição |
| :--- | :--- |
| **Java 21** | Versão LTS moderna com alta performance e novos recursos da linguagem. |
| **Spring Boot** | Framework base para produtividade e arquitetura corporativa. |
| **Spring Data JPA / Hibernate** | Abstração da camada de persistência e mapeamento objeto-relacional. |
| **PostgreSQL** | Banco de dados relacional robusto para integridade das relações. |
| **Flyway Migration** | Controle e versionamento automatizado do esquema de banco de dados (`db/migration`). |
| **SpringDoc OpenAPI (Swagger UI)** | Documentação viva e interativa dos endpoints REST. |
| **Spring Security** | Autenticação, autorização e controle de acessos por roles (`ADMIN` / `USER`). |
| **Auth0 Java JWT** | Geração e validação de tokens JWT para autenticação *stateless*. |
| **Docker & Docker Compose** | Containerização da API e do PostgreSQL para execução padronizada. |
| **Jakarta Bean Validation** | Validações declarativas nos DTOs (`@NotBlank`, `@NotNull`, `@Positive`). |
| **Lombok** | Produtividade no código eliminando getters, setters e construtores repetitivos. |
| **Maven Wrapper (`mvnw`)** | Gerenciador de dependências e build integrado. |

---

## 🚀 Como Rodar com Docker (Recomendado)

A forma mais rápida de subir a API + PostgreSQL sem precisar instalar Java, Maven ou banco de dados localmente:

1. Certifique-se de ter o **Docker** (ou Docker Desktop) instalado e rodando.
2. Clone o repositório e entre na pasta do projeto:
   ```bash
   git clone <url-do-repositorio>
   cd jogosapi
   ```
3. Suba os containers:
   ```bash
   docker compose up --build
   ```
4. A API estará disponível em `http://localhost:8080` e o Flyway criará automaticamente todas as tabelas (`V1` a `V6`).
5. Para encerrar os containers:
   ```bash
   docker compose down
   ```

---

## 📖 Documentação e Testes (Swagger UI)

Com a aplicação rodando, acesse no navegador:
👉 **`http://localhost:8080/swagger-ui.html`**

### 🔐 Credenciais do Administrador Padrão
Para testar rotas administrativas (como cadastrar estúdios, jogos ou listar todas as vendas), a migration `V6` já cria automaticamente um usuário `ADMIN`:

* **Email:** `admin@aokrys.com`
* **Senha:** `admin123`

**Como autenticar no Swagger:**
1. Execute o endpoint **`POST /auth/login`** com as credenciais acima (ou crie uma conta comum em **`POST /usuarios`**).
2. Copie o `token` retornado.
3. Clique no botão **Authorize** (cadeado) no topo do Swagger, cole o token e confirme.

---

## 💻 Como Rodar Localmente (Sem Docker)

1. Copie o arquivo `src/main/resources/application-example.yaml` para `src/main/resources/application.yaml`.
2. Preencha `spring.datasource.url`, `username`, `password` com os dados do seu PostgreSQL local e defina uma chave em `api.security.token.secret`.
3. Execute pelo IntelliJ ou via terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
