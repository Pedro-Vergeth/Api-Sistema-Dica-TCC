# Api Sistema Dica TCC

## Passo a passo para executar o projeto com Docker

### 1. Clonar o projeto do GitHub

```bash
git clone https://github.com/Pedro-Vergeth/Api-Sistema-Dica-TCC
cd Api-Sistema-Dica-TCC
```

### 2. Modificar os dados de acesso no `docker-compose.yml`

Altere os dados de acesso do banco de dados, a chave secreta do JWT e os demais dados necessários nas variáveis do Spring no arquivo `docker-compose.yml`.

**Observação:** os dados do banco de dados devem ser os mesmos em `application.properties` e `docker-compose.yml`.

Exemplo de variáveis que devem ser ajustadas:

```yaml
environment:
  SPRING_DATASOURCE_USERNAME: acesso1
  SPRING_DATASOURCE_PASSWORD: acesso1tcc4323
  JWT_SECRET: 253245245
```

### 3. Configurar o `application.properties`

Adicione o arquivo `src/main/resources/application.properties` com os seguintes dados:

```properties
spring.application.name=dica-br-api
spring.datasource.url=jdbc:postgresql://localhost:5432/DicaBD
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:acesso1}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:acesso1tcc4323}
spring.datasource.driver-class-name=org.postgresql.Driver
api.security.token.secret=${JWT_SECRET:253245245}
api.security.token.expiration=60

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB

app.site.url=http://localhost:5173
app.app.url=http://localhost:8081
app.cors.allowed-origins=${app.site.url},${app.app.url}

ollama.api.url=http://localhost:11434/api/generate
ollama.api.model=gemma4:e2b
ollama.api.prompt=Voce e um assistente de nutricao visual. Analise a imagem e identifique o alimento principal. Regra critica: Responda ESTRITAMENTE E APENAS com o nome do alimento. Nao use pontuacao, nao crie frases, nao diga 'A imagem mostra'. Exemplo de saida desejada: Arroz. Caso nao reconheca, retorne: "Alimento desconhecido".
```

### 4. Configurar o `application-docker.properties`

Adicione o arquivo `src/main/resources/application-docker.properties` com os seguintes dados:

```properties
spring.datasource.url=jdbc:postgresql://postgres-db:5432/DicaBD
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:acesso1}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:acesso1tcc4323}
spring.datasource.driver-class-name=org.postgresql.Driver

app.site.url=http://localhost:5173
app.app.url=http://localhost:8081
app.cors.allowed-origins=${app.site.url},${app.app.url}

ollama.api.url=http://ollama-ai:11434/api/generate
ollama.api.model=gemma4:e2b
```

Se quiser alterar para outro ambiente, você pode modificar:

- `spring.datasource.url`
- `app.site.url`
- `app.app.url`
- `ollama.api.url`

### 5. Subir os containers

Execute o comando no terminal:

```bash
docker-compose up --build -d
```

### 6. Baixar o modelo do Ollama

Execute o comando no terminal:

```bash
docker exec -it dica_ollama ollama run gemma4:e2b
```

Esse comando faz o download do modelo `gemma4:e2b` no Ollama.

> Você pode usar outro modelo superior, mas nesse caso também será necessário alterar o valor de `ollama.api.model` em `application.properties` e `application-docker.properties`.

## Observações importantes

- Os dados do banco de dados devem ser iguais em `application.properties` e `docker-compose.yml`.
- Se o front estiver rodando em outra porta, ajuste:
  - `app.site.url`
  - `app.app.url`
- Se o modelo do Ollama mudar, ajuste:
  - `ollama.api.model`
- Caso o nome do container do Ollama seja diferente, altere o comando `docker exec` conforme o nome real do container.

## Acesso

Após subir a aplicação, a API ficará disponível na porta configurada no `docker-compose.yml`.

