# Serviço de Encurtador de URL

Bem-vindo ao Serviço de Encurtador de URL! Este projeto fornece uma maneira simples e eficiente de encurtar URLs, acompanhar contagens de acesso e recuperar dados de URLs.


## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **MongoDB**
- **Lombok**

## 📑 Endpoints

### 1. Encurtar URL
**Endpoint:** `POST /shorten`

**Corpo da Requisição:**
```json
{
  "url": "https://github.com/Jaoow"
}
```
**Resposta:** 
```json
{
  "id": "u3NQu",
  "shortUrl": "http://localhost:8080/u3NQu",
  "expiresAt": "2024-05-29T09:21:42.4064278"
}
```
Este endpoint gera uma URL encurtada para a URL original fornecida.

### 2. Redirecionar para a URL Original
**Endpoint:** `GET /{id}`

**Resposta:** Redireciona para a URL original se a URL encurtada existir, caso contrário, retorna um código de status 404.

### 3. Obter Dados da URL Encurtada
**Endpoint:** `GET /info/{id}`

**Resposta:**
```json
{
    "id": "abc123",
    "originalURL": "https://exemplo.com",
    "accessCount": 10,
    "lastAccessed": "2024-05-29T14:32:22.123",
    "expiresAt": "2024-05-29T15:37:22.123"
}
```
Este endpoint recupera dados sobre a URL encurtada, incluindo a URL original, contagem de acessos, última vez acessada e tempo de expiração.

## 🛠 Como Executar

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/Jaoow/url-shortener-service.git
    cd url-shortener-service
    ```

2. **Construa o projeto:**
    ```bash
    ./mvnw clean install
    ```

3. **Execute a aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Acesse o serviço:**
   O serviço estará disponivel no URL `http://localhost:8080`.

## 📝 Notas

- As URLs encurtadas geradas expirarão 1 dia após a criação.
- As contagens de acesso são incrementadas cada vez que a URL encurtada é acessada.
- O serviço usa um banco de dados MongoDB.

---

Feito com ❤️ por [Jaoow](https://github.com/Jaoow)
