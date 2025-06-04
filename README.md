Esta é uma API de gestão financeira. A API permite gerenciar contas e realizar transações financeiras, além de consultar o histórico de transações de uma conta.

Endpoints

1. Salvar Conta

Endpoint: /conta/salvar
Método: POST


Exemplo de Chamada:
curl --location 'localhost:8080/conta/salvar' \
--header 'Content-Type: application/json' \
--data '{
    "numeroConta": 123,
    "saldoInicial": 900
}'

2. Efetuar Transação

Endpoint: /transacao/efetuaTransacao
Método: POST


Exemplo de Chamada:
curl --location 'localhost:8080/transacao/efetuaTransacao' \
--header 'Content-Type: application/json' \
--data '{
    "tipo": "P",
    "numConta": 123,
    "valor": 150
}'


3. Buscar Conta

Endpoint: /buscar/{numeroConta}
Método: GET


Exemplo de Chamada:
curl --location --request GET 'localhost:8080/buscar/1234' \
--header 'Content-Type: application/json'

4. Extrato de Transações

Endpoint: /extrato/{numeroConta}
Método: GET


Este endpoint permite buscar o histórico de transações de uma conta específica.


Exemplo de Chamada:
curl --location --globoff --request GET 'localhost:8080/extrato/{numeroConta}' \
--header 'Content-Type: application/json'

Utilizei o banco de dados em memória H2 que após o início da aplicação pode ser acessado em: http://localhost:8080/h2-console/login.jsp?jsessionid=33af55e3112a55a9567dc7a08eb0bb41
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Não preencher o Password

