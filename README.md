# 🎭 IngressArt – Projetos 03

Este é o repositório do projeto **IngressArt**, desenvolvido na disciplina **Projetos 03**.

O objetivo é criar uma solução de **gestão de teatros na cidade do Recife**, modernizando processos internos e otimizando a experiência do público — desde a **compra de ingressos** até a **análise de dados sobre os espectadores** e **geração de relatórios** para os administradores.

<details>
<summary>👥 Equipe</summary>

Nosso time é formado por seis integrantes. Abaixo, estão seus respectivos GitHubs e contatos:

- **Bruno Oliveira**  
  GitHub: [bruno-omf](https://github.com/bruno-omf)  
  E-mail: <bomf@cesar.school>

- **Karoline Andrade**  
  GitHub: [kass200](https://github.com/kass200)  
  E-mail: <kass@cesar.school>

- **Jorge Augusto**  
  GitHub: [Jaabsolutaa](https://github.com/Jaabsolutaa)  
  E-mail: <jalv@cesar.school>

- **Maria Luisa**  
  GitHub: [malualbuquerqueb](https://github.com/malualbuquerqueb)  
  E-mail: <mlabc@cesar.school>

- **Matheus Miranda**  
  GitHub: [MatheusMiraEsc](https://github.com/MatheusMiraEsc)  
  E-mail: <mme@cesar.school>

- **Pedro Augusto**  
  GitHub: [pedroooojh](https://github.com/pedroooojh)  
  E-mail: <pascd@cesar.school>

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
- **Diagrama de Classes atualizado**  
![Class Diagram2](https://github.com/user-attachments/assets/98c104f2-6cdc-4da7-b0d5-2076a3897b62)



- Testes completos e validação com usuários  
- Documentação e apresentação final  
- Preparação para publicação ou uso real

- **Screencast**
 [YouTube](https://www.youtube.com/watch?v=doPFZZxHdfw)

</details>

</details>

---

<details>
<summary> IngressArt - Como rodar?</summary>

## ✅ Requisitos para rodar o projeto

**Requisitos:**

- Java 17 ou superior  
- PostgreSQL instalado e em execução  
- Maven instalado!!  
- IDE compatível com Java (IntelliJ IDEA, Eclipse, VS Code etc.)

---

## ▶️ Passos para executar o projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/MatheusMiraEsc/projetos-3-si-equipe-3.git
   cd projetos-3-si-equipe-3
   ```

2. **Crie um banco de dados no PostgreSQL:**
  Abra o terminal do PostgreSQL (psql), logue com o usuario postgres e senha postgres e execute o seguinte comando:

   ```sql
   CREATE DATABASE ingressart;
   ```

3. **Configure o banco de dados:**
  Abra o arquivo schema.sql na pasta `src/main/java/ingressart/teatro/database` e execute os comandos SQL contidos nele no terminal do PostgreSQL (psql) para criar as tabelas necessárias.

4. **Compile o projeto:**
   No terminal da sua IDE, execute o seguinte comando para compilar o projeto:
   Antes, certifique-se de que o Maven está instalado e configurado corretamente e se você está na pasta raiz do projeto.

   ```bash
   mvn clean install
   ```

5. **Execute o projeto:**
    Após a compilação, execute o seguinte comando para iniciar o projeto:
  
    ```bash
    java -jar target/ingressart-teatro-1.0.0.jar
    ```

6. **Acesse o sistema:**
    Após a execução, o sistema estará disponível no terminal :D

---

### Fluxo de Funcionamento

Acesso Inicial
Ao iniciar o sistema, o usuário escolhe:

- Acessar como Teatro (Administrador)
- Acessar como Cliente
- Acessar sem cadastro
- Sair

Teatro (Administrador)

- Menu disponível:
- Cadastrar Peça
  - Listar Peças
  - Editar Peça
  - Deletar Peça
  - Sair
- Cadastrar Sala
- Listar Salas
- Cadastrar Sessão
- Sair

Detalhes:

- Cada peça está vinculada a uma sala e possui sessões.
- A capacidade da sessão segue a capacidade da sala.
- A edição e exclusão de peças afetam também suas sessões.

Cliente
Acesso pode ser com ou sem login.

- Sem login:
- Visualiza peças cadastradas
- Vê detalhes da peça
- Se quiser comprar ingresso, precisa se cadastrar

- Com login:
- Visualiza peças
- Compra ingresso via simulação PIX
- Recebe comprovante com código

Pode acessar a opção Meus Eventos (em construção)
</details>

---

## 📄 Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
