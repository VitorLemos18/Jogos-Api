🎮 Aokrys GameStore API — Documentação do Projeto
💡 A Ideia da API

A Aokrys GameStore API é uma API RESTful projetada para servir como o núcleo de backend de uma plataforma de jogos. Seu foco é organizar e disponibilizar de forma estruturada as relações entre Jogos, Estúdios/Empresas Desenvolvedoras e Categorias/Gêneros:

Gestão de Empresas (Estúdios/Publishers): Cadastro e consulta de estúdios com histórico, data de fundação e a capacidade de listar o portfólio completo de jogos pertencentes a cada empresa.
Classificação por Categorias: Categorização flexível dos jogos (um jogo pode pertencer a múltiplos gêneros como RPG, Ação, Indie).
Catálogo e Consulta: Endpoints para listagem, filtros combinados (jogos por empresa, jogos por categoria) e consulta detalhada de preços e estoque.
Controle de Usuários e Segurança: Gerenciamento de acessos de clientes e administradores com autenticação segura.
🛠️ Tecnologias Utilizadas na API
Linguagem: Java 21
Framework: Spring Boot
Spring Data JPA / Hibernate: Mapeamento objeto-relacional (ORM) e consultas ao banco.
Spring Web (MVC): Criação dos controladores REST e manipulação de requisições/respostas HTTP.
Spring Security: Camada de autenticação e controle de permissões por roles (ADMIN, USER).
Bean Validation (Jakarta Validation): Validação dos dados recebidos nos DTOs.
Banco de Dados & Migrações:
PostgreSQL
: Banco de dados relacional.
Flyway
: Versionamento e execução automatizada de scripts SQL de migração (db/migration).
Documentação Interativa:
SpringDoc OpenAPI / Swagger UI
: Interface gráfica para testar e documentar os endpoints diretamente no navegador (/swagger-ui.html).
Utilitários:
Lombok
: Redução de código boilerplate (@Getter, @Setter, construtores).