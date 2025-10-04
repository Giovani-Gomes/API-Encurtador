# Encurtador de URL 

Este é um simples encurtador de URL desenvolvido utilizando Java, Spring Boot e banco de dados em memória H2. O projeto permite que você envie uma URL longa e receba uma versão encurtada dela, além de poder redirecionar para a URL original usando o código encurtado.

## Funcionalidades

- **Encurtar URL**: Envia uma URL longa via POST e recebe a URL encurtada.
- **Redirecionamento**: A URL encurtada redireciona para o endereço original ao ser acessada via GET.

## Tecnologias Utilizadas

- **Java 17** 
- **Spring Boot** (para construção da API)
- **H2 Database** (para armazenar as URLs originais e encurtadas)
- **Maven** (para gerenciamento de dependências)

## Endpoints da API

### 1. Encurtar uma URL

#### `POST /shorten`

Esse endpoint recebe uma URL longa e retorna a URL encurtada.

**Request:**

```json
{
  "url": "https://www.exemplo.com/algum-caminho"
}
