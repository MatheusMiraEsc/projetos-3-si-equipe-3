# 🎭 IngressArt – Projetos 03

Este é o repositório do projeto **IngressArt**, desenvolvido na disciplina **Projetos 03**.

O objetivo é criar uma solução de **gestão de teatros na cidade do Recife**, modernizando processos internos e otimizando a experiência do público — desde a **compra de ingressos** até a **análise de dados sobre os espectadores** e **geração de relatórios** para os administradores.

<details>
<summary>👥 Equipe</summary>

Nosso time é formado por seis integrantes. Abaixo, estão seus respectivos GitHubs e contatos:

- **Bruno Oliveira**  
  GitHub: [bruno-omf](https://github.com/bruno-omf)  
  E-mail: bomf@cesar.school

- **Karoline Andrade**  
  GitHub: [kass200](https://github.com/kass200)  
  E-mail: kass@cesar.school

- **Jorge Augusto**  
  GitHub: [Jaabsolutaa](https://github.com/Jaabsolutaa)  
  E-mail: jalv@cesar.school

- **Maria Luisa**  
  GitHub: [malualbuquerqueb](https://github.com/malualbuquerqueb)  
  E-mail: mlabc@cesar.school

- **Matheus Miranda**  
  GitHub: [MatheusMiraEsc](https://github.com/MatheusMiraEsc)  
  E-mail: mme@cesar.school

- **Pedro Augusto**  
  GitHub: [pedroooojh](https://github.com/pedroooojh)  
  E-mail: pascd@cesar.school

</details>

---

<details>
<summary>🔗 Links Importantes</summary>

- **Google Drive do Projeto**  
  [Acessar Google Drive](https://drive.google.com/drive/folders/1i39c-0Pjjzu1giN-jWWBlgzjGbyWYoW_?usp=sharing)

</details>

---

<details>
<summary>📦 Entregas</summary>


<details>
<summary>📍 Entrega 1</summary>

- **Histórias de Usuário**  
  [Ver Documento](https://docs.google.com/document/d/1xR2WzLU8VZLKRwLLtpqHdbMdx1_yzDKc0HukmepcWw8/edit?usp=sharing)

- **Sketch Inicial (Figma)**  
  [Ver no Figma](https://www.figma.com/board/r7o7DKTGKAZRDjI0sXXUfe/Projetos-3?node-id=0-1&t=j7Ymv4OdVUhpAi1l-1)

- **Protótipo LO-FI**  
  [Ver no Figma](https://www.figma.com/design/4wSNGq8mUJAOu6osnInCal/LO-FI---IngressArt?node-id=0-1&t=PW0UFo2tPWoakhqY-1)

- **Screencast**  
  [Google Drive](https://drive.google.com/file/d/1qsG7LR3z1AAgocyJOQwtKVRx-g7BBCJX/view?usp=drive_link)  
  [YouTube](https://youtu.be/9tPTPO0eYSs)

</details>

<details>
<summary>📍 Entrega 2</summary>
  
- **Diagrama de Classes**  

![Class Diagram2](https://github.com/user-attachments/assets/7a84ccd0-9294-449a-a334-ac31077a0bd0)

- **Screencast das histórias**
 [Google Drive](https://drive.google.com/file/d/1QlMDrYOWScaeN313fvkxH59cj_X_r5WE/view?usp=drive_link)  
 [YouTube](https://youtu.be/Tp9b67hkByM)


</details>

<details>
<summary>📍 Entrega 3</summary>

- Início da implementação das principais funcionalidades  
- Integração entre telas e lógica de dados  
- Testes iniciais e ajustes com base no feedback  
- Apresentação de um MVP funcional

- **Screencast**
 [YouTube](https://www.youtube.com/watch?v=zHXmr4WWMyQ)

</details>

<details>
<summary>📍 Entrega 4</summary>

- Projeto final consolidado  
- Testes completos e validação com usuários  
- Documentação e apresentação final  
- Preparação para publicação ou uso real  

</details>

</details>

---


<details>
<summary> IngressArt - Como rodar?</summary>

Este projeto é composto por dois módulos:

- **Backend**: Java + Spring Boot + PostgreSQL
- **Frontend**: React (Vite) com consumo da API Java



###  Requisitos para rodar o projeto

### Backend - Java
- Java 17 ou superior
- Maven
- PostgreSQL
- IDE sugerida: Eclipse

### Frontend - React
- Node.js (versão 18 ou superior)
- npm

---


---

### Configuração do Banco de Dados (PostgreSQL)

1. Abra o **pgAdmin** ou seu terminal do PostgreSQL.
2. Crie um banco de dados com o nome: java_backend
3. Usuário: `postgres`  
   Senha: `postgres` (ou a que você definiu na instalação)

---

### Como rodar o Backend (Spring Boot)

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

</details>

---

## 📄 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).



