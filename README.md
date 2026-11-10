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
| **Spring Security** | Autenticação, autorização e controle de acessos por roles. |
| **Jakarta Bean Validation** | Validações declarativas nos DTOs (`@NotBlank`, `@NotNull`, `@Positive`). |
| **Lombok** | Produtividade no código eliminando getters, setters e construtores repetitivos. |
| **Maven Wrapper (`mvnw`)** | Gerenciador de dependências e build integrado. |
