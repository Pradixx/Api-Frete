# 📦 Frete API

API REST para cálculo e cotação de frete entre diferentes transportadoras, desenvolvida com Spring Boot e Java 21.

---

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Docker / Docker Compose**

---

## 📋 Funcionalidades

- Recebe os dados de um produto (dimensões, peso, CEP de destino e valor declarado)
- Calcula o custo de frete para múltiplas transportadoras
- Persiste os dados do produto no banco de dados
- Retorna as opções de frete ordenadas pelo menor custo

### Transportadoras disponíveis

| Transportadora     | Cálculo base                          | Prazo |
|--------------------|---------------------------------------|-------|
| Enjoei Express     | Volume cúbico (m³) × 50 + R$10,00    | 3 dias |
| Peso Pesado Log    | Peso (kg) × R$3,50 + R$5,00          | 5 dias |

> Ambas as transportadoras incluem seguro de 1% sobre o valor declarado.

---

## ⚙️ Como executar

### Pré-requisitos

- Java 21+
- Maven
- Docker e Docker Compose

### 1. Suba o banco de dados

```bash
docker-compose up -d
```

### 2. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📡 Endpoints

### `POST /api/frete`

Calcula e retorna as opções de frete para um produto.

**Request body:**

```json
{
  "cepDestino": "01310100",
  "pesoKG": 5.0,
  "alturaCM": 30.0,
  "larguraCM": 20.0,
  "comprimentoCM": 40.0,
  "valorDeclarado": 500.0
}
```

**Response:**

```json
[
  {
    "transportadora": "Enjoei Express",
    "custoTotal": 21.00,
    "prazo": 3
  },
  {
    "transportadora": "Peso Pesado Log",
    "custoTotal": 27.50,
    "prazo": 5
  }
]
```

> As opções são retornadas ordenadas pelo menor custo total.

---

## 🗄️ Configuração do banco de dados

As configurações padrão do `application.properties` já estão alinhadas com o `docker-compose.yml`:

| Propriedade | Valor         |
|-------------|---------------|
| Host        | localhost:3306 |
| Database    | mydatabase    |
| Username    | myuser        |
| Password    | secret        |

---

## 🏗️ Estrutura do projeto

```
src/
├── main/
│   └── java/com/elo7/frete/
│       ├── controller/
│       │   ├── DTO/
│       │   │   ├── FreteResponseDTO.java
│       │   │   └── SalvarProdutoDTO.java
│       │   └── FreteController.java
│       ├── infrastructure/
│       │   ├── entity/
│       │   │   └── Produto.java
│       │   └── repository/
│       │       └── ProdutoRepository.java
│       ├── service/
│       │   └── FreteService.java
│       └── FreteApplication.java
└── test/
    └── java/com/elo7/frete/
        └── FreteApplicationTests.java
```

---

## 📄 Licença

Este projeto foi desenvolvido como demonstração técnica com Spring Boot.
