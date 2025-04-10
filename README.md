# Projetos 03 - IngressArt

Este é o repositório do nosso projeto desenvolvido na disciplina de Projetos 03: gestão de projetos. O objetivo do projeto é desenvolver uma plataforma web com o objetivo de automatizar e facilitar a gestão e vendas de ingressos para teatros de Recife. Queremos facilitar a vida tanto dos funcionários de teatros, quanto do público, desde a venda dos ingressos até a coleta de informações sobre espectadores e a geração de relatórios consolidados.

## Apresentação(a ser atualizado)

Nosso time é composto por seis membros, cada um trazendo um conjunto único de habilidades e contribuições para o projeto. Abaixo, você pode conhecer um pouco mais sobre cada integrante da equipe.

### Bruno Oliveira de Macêdo Filho
**Habilidades:** 
- Programação em python
- Conhecimento de Front-End
- Conhecimento em processos na engenharia de software

**Contribuições para o projeto:**
- Organização e planejamento geral do projeto.
- Processos de construção de software e Gitflow.
- Construção do backlog do produto

### Karoline...(a ser atualizado)
**Habilidades:** 
- Organização
- Conhecimento em Python
- Conhecimento em HTML e CSS

**Contribuições para o projeto:**
- Design de slides.
- Prototipação Lo-fi e mockup.
- Organização do cronograma.

### Jorge Augusto Lacerda de Vasconelos
**Habilidades:** 
- Relações Interpessoais 
- Conhecimento em Python
- Raciocínio Lógico

**Contribuições para o projeto:**
- Documentação geral do projeto.
- Geração de hipóteses e dúvidas.
- Ciclo de vida do projeto.

### Maria Luisa Albuquerque B. Carvalho
**Habilidades:** 
- Conhecimento prático de figma
- Conhecimento prático de HTML, CSS, JS
- Designs com Canva

**Contribuições para o projeto:**
- Sugestão de ideias.
- Design geral do projeto.
- Prototipação Lo-fi e mockup.

### Matheus Miranda Escorel
**Habilidades:** 
- Planejamento e gestão de pessoas
- Programação em Python
- Resolução de problemas

**Contribuições para o projeto:**
- Organização e planejamento geral do projeto.
- Documentação geral do projeto.
- Construção do backlog do produto

### Pedro Augusto Simões Calazans Dutra
**Habilidades:** 
- Conhecimento em Python
- Conhecimento em edição e manipulação de imagem
- Resolução de Problemas

**Contribuições para o projeto:**
- Documentação geral do projeto.
- Pesquisa de soluções similares.
- Design de slides.

## Screeancast


*link do screencast*



# 🎭 IngressArt - Sistema de Cadastro de Peças de Teatro

Este projeto é composto por dois módulos:

- **Backend**: Java + Spring Boot + PostgreSQL
- **Frontend**: React (Vite) com consumo da API Java

---

##  Requisitos para rodar o projeto

### Backend - Java
- Java 17 ou superior
- Maven
- PostgreSQL
- IDE sugerida: Eclipse

### Frontend - React
- Node.js (versão 18 ou superior)
- npm

---

## Estrutura de Pastas 
IngressArt/ 
├── java-backend/ <- Projeto Java Spring Boot │ 
├── src/ │ ├── pom.xml │ 
└── ... 
├── teatro-frontend/ <- Projeto React com Vite │
├── src/ │ 
├── package.json │ 
└── ... 
└── README.md <-

---

## 🐘 Configuração do Banco de Dados (PostgreSQL)

1. Abra o **pgAdmin** ou seu terminal do PostgreSQL.
2. Crie um banco de dados com o nome: java_backend
3. Usuário: `postgres`  
   Senha: `postgres` (ou a que você definiu na instalação)

---

## 🚀 Como rodar o Backend (Spring Boot)

### 1. Abrir o projeto no Eclipse

- Importe a pasta `java-backend` como projeto Maven.

### 2. Configurar o `application.properties`

Verifique se o arquivo `src/main/resources/application.properties` está assim:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/java_backend
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
````

### Rodar o projeto
- Clique com o botão direito no projeto > Run As > Spring Boot App

###  Como rodar o Frontend (React + Vite)

1. Abrir o terminal
Abra um terminal na pasta teatro-frontend.

2. Instalar dependências
bash
npm install

3. Rodar o frontend
bash
npm run dev

Abra o navegador em:
http://localhost:5173


### Fluxo de Funcionamento
- A tela inicial exibe o formulário para cadastrar uma peça de teatro.

- O formulário envia os dados para o backend em http://localhost:8080/pecas.

- A opção "Visualizar Peças" na navbar lista todas as peças cadastradas consumindo a mesma API.
   


## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
