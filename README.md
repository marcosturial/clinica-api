# clinica-api

Esse projeto foi implementado com Quarkus, um framework Java.

Para saber mais sobre o Quarkus, acessar: <https://quarkus.io/>.

## Rodando a aplicação no modo de desenvolvimento

É possível rodar a aplicação em modo de desenvolvimento sem instalar nada, utilizando o seguinte comando:

```shell script
./mvnw quarkus:dev
```

## Gerando um jar e executando a aplicação

O jar da aplicação pode ser gerado com o seguinte comando:

```shell script
./mvnw package
```

Será produzido o executável `quarkus-run.jar` na pasta `target/quarkus-app/`.

Agora é possível executar a aplicação utilizando o comando `java -jar target/quarkus-app/quarkus-run.jar`.

## Testando a api
A aplicação será gerada por padrão na porta 8080.
É possível testar a api acessando o swagger, implementado no endereço http://localhost:8080/api
