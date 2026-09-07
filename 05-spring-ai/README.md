# 🚀 Finance AI - API de Orçamento com Spring AI & Spring Boot

API de Gestão Financeira multimodal desenvolvida durante a trilha de Spring Boot da DIO. A aplicação permite cadastrar e consultar transações financeiras por comandos de voz e texto usando Spring AI, além de disponibilizar endpoints REST padronizados.

---

## 🎯 Objetivo do Projeto
Integrar o ecossistema **Spring AI** (com modelos de transcrição Speech-to-Text e ChatClient com Tool Calling) em uma arquitetura em camadas bem definida, sem violar as regras de negócio do domínio.

---

## ✨ Melhorias Implementadas na Entrega

### 1. 📊 Consulta de Total de Transações por Categoria (Business Tool)
- **Caso de Uso:** Criada a classe `GetTotalByCategoryUseCase` que utiliza Java Stream API (`mapToLong().sum()`) para somar valores de transações filtradas por categoria.
- **Spring AI Tool Calling:** Método anotado com `@Tool(name = "total-amount-by-category")`, permitindo que a IA interprete comandos de voz/texto (ex: *"Quanto gastei em alimentação?"*) e acione o cálculo de forma automática.
- **Endpoint REST:** Disponibilizado o endpoint GET `/total/{category}`.

### 2. 🛡️ Validação de Dados & Tratamento Global de Exceções
- **Bean Validation no DTO (`TransactionRequest`):**
  - `@NotBlank` na descrição (evita valores nulos ou vazios).
  - `@NotNull` na categoria.
  - `@Positive` no valor (`amount`), garantindo apenas valores maiores que zero.
- **Tratamento de Exceções (`GlobalExceptionHandler`):**
  - Anotado com `@RestControllerAdvice`.
  - Captura de `MethodArgumentNotValidException` e `HttpMessageNotReadableException`.
  - Retorna respostas de erro estruturadas e amigáveis com status HTTP 422 (`UNPROCESSABLE_ENTITY`), timestamp e campo específico do erro.

---

## 🛠️ Tecnologias Utilizadas
- **Java 25 / Spring Boot 4**
- **Spring AI** (ChatClient & Tool Calling)
- **Spring Data JPA**
- **Spring Validation (Hibernate Validator)**
- **Gradle**

---

## 🧪 Como Testar

### 1. Defina sua chave de API da OpenAI:

```bash
export OPENAI_API_KEY="your_api_key_here"
```

### 2. Execute o aplicativo e os testes:

```bash
./gradlew bootRun
./gradlew test
```

### 3. Testando as Validações (REST)
Tente enviar um POST para criar uma transação com valor negativo ou descrição vazia:
```json
POST /transactions
{
  "description": "",
  "category": "ALIMENTACAO",
  "amount": -50
}
