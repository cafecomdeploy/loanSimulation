# 💰 API RESTful de Simulação de Empréstimos

Bem-vindo(a) à API de Simulação de Empréstimos! 🚀  
Este projeto tem como objetivo fornecer uma interface simples, segura e eficiente para simular empréstimos com base em diferentes parâmetros fornecidos pelo usuário.

---

## 📚 Sumário

- [📦 Sobre o Projeto](#-sobre-o-projeto)
- [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [📡 Endpoints da API](#-endpoints-da-api)
- [🧪 Como Executar o Projeto Localmente](#-como-executar-o-projeto-localmente)
- [✅ Testes](#-testes)
- [📬 Contato](#-contato)

---

## 📦 Sobre o Projeto

Essa API realiza a **simulação de empréstimos**, retornando informações como:

- Valor total a pagar
- Parcelas mensais
- Juros aplicados
- Validação de dados do solicitante

Ideal para ser integrada em sistemas financeiros, CRMs ou plataformas de análise de crédito. 🔍

---

## ⚙️ Tecnologias Utilizadas

🛠️ Back-end desenvolvido com:

- Java 17 ☕
- Spring Boot
- Maven
- JPA / Hibernate
- Banco de dados H2 (para testes) ou PostgreSQL
- Swagger/OpenAPI para documentação
- JUnit + Mockito para testes

---

## 📡 Endpoints da API

### 🔹 `POST /simulacoes`
Realiza a simulação de um novo empréstimo.

**Requisição:**
```json
{
  "nome": "João da Silva",
  "cpf": "12345678900",
  "valor": 10000,
  "parcelas": 12,
  "taxaJuros": 2.5
}
```

**Resposta:**
```json
{
  "valorTotal": 11345.23,
  "valorParcela": 945.43,
  "quantidadeParcelas": 12,
  "taxaJuros": 2.5
}
```

### 🔹 `GET /simulacoes`
Lista todas as simulações realizadas.

### 🔹 `GET /simulacoes/{id}`
Retorna os detalhes de uma simulação específica.

### 🔹 `DELETE /simulacoes/{id}`
Remove uma simulação do sistema.

---

## 🧪 Como Executar o Projeto Localmente

```bash
# Clone o repositório
git clone https://github.com/cafecomdeploy/loanSimulation.git

# Acesse a pasta do projeto
cd loanSimulation

# Compile o projeto
./mvnw clean install

# Rode a aplicação
./mvnw spring-boot:run
```

A API estará disponível em: [http://localhost:8009](http://localhost:8009)

Acesse a documentação Swagger em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## ✅ Testes

Execute os testes automatizados com:

```bash
./mvnw test
```

Os testes cobrem:

- Regras de cálculo dos juros
- Validações de entrada
- Regras de negócio

---

## 📬 Contato

💡 Desenvolvido por **Lorena Fernandes**  
📧 Email: lorena.cunha@hotmail.com
🔗 LinkedIn: https://www.linkedin.com/in/lorenafernandesc/
