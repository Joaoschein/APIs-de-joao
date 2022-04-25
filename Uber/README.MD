# Trabalho 02 - Me Leva Aí!

## Grupo:
    João Marcelo Schein

## Endpoints

---

### MOD 02
 - PUT `http://localhost:8080/passageiro/{id}/inativar` e `http://localhost:8080/motorista/{id}/inativar`
    - Altera o estado do passageiro ou motorista com id informado para `INATIVO`

---

### REQ 01
 - GET `http://localhost:8080/passageiro`
    - Lista todos os passageiros `LIVRES` 

---

### REQ 02
 - POST `http://localhost:8080/corrida`

    ```json
    {
        "id": int,
        "pontoInicialX": Double,
        "pontoInicialY": Double,
        "pontoFinalX": Double,
        "pontoFinalY": Double
    }
    ```
    
    - Solicita uma corrida para o passageiro com o id informado no body.
    
---

### REQ 03
 - PUT `http://localhost:8080/corrida/{id}/iniciar`

    - Inicia a corrida com o id informado.
    
---

### REQ 04
 - PUT `http://localhost:8080/corrida/{aid}/finalizar`

    - Finaliza a corrida com o id informado. (Não é possivel finalizar uma corrida antes do horario de chegada).

---

### REQ 05
 - PUT `http://localhost:8080/passageiro/{id}/depositar`

    ```json
    {
        "valor": BigDecimal
    }
    ```
    
    - Deposita o valor informado na conta do passageiro com o id informado.
    
---

### REQ 06
 - PUT `http://localhost:8080/motorista/{id}/sacar`

    ```json
    {
        "valor": BigDecimal
    }
    ```
    
    - Retira o valor informado da conta do motorista com id informado.
    
---

### REQ 07
 - PUT `http://localhost:8080/corrida/{id}/avaliar/passageiro`

    ```json
    {
        "nota": int
    }
    ```
    
    - Avalia o passageiro da corrida com o id informado.
    
---

### REQ 08
 - PUT `http://localhost:8080/corrida/{id}/avaliar/motorista`

    ```json
    {
        "nota": int
    }
    ```
    
    - Avalia o motorista da corrida com o id informado.
    
---

### OUTROS
 - GET `http://localhost:8080/motorista`
    - Retorna uma lista com todos os motoristas `OCUPADOS` e algumas informações das corridas em que estão.

 - GET `http://localhost:8080/passageiro/{id}` e `http://localhost:8080/motorista/{id}`
    - Retorna as informações do motorista ou passageiro informado por id.
 