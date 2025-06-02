# Projeto Arduino - Controle de Lâmpada via Web

Este projeto integra uma aplicação web feita com Spring Boot a um Arduino via porta serial, permitindo controlar uma lâmpada virtual e registrar logs de ações no banco de dados MySQL.

---

## Tecnologias Utilizadas

- Java (JDK 17+)
- Spring Boot
- Thymeleaf
- RXTX (comunicação serial)
- MySQL
- HTML + CSS
- Arduino UNO

---

## Funcionalidades

- Interface web para ligar/desligar lâmpada com clique em imagem
- Comunicação com Arduino pela porta serial
- Registro de logs no banco de dados com timestamp
- Exibição de tabela com histórico de comandos
- Botão para limpar os logs

---

## Como Executar

### Passo 1 - Banco de Dados MySQL

Crie o banco de dados:

```sql
CREATE DATABASE lampada;
```

O Spring criará a tabela automaticamente com base no model.

---

### Passo 2 - Configure o application.properties

No arquivo `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/lampada
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### Passo 3 - Código do Arduino

Use esse sketch no Arduino:

```cpp
void setup() {
  Serial.begin(9600);
  pinMode(13, OUTPUT);
}

void loop() {
  if (Serial.available()) {
    char comando = Serial.read();
    digitalWrite(13, comando == '1' ? HIGH : LOW);
  }
}
```

---

### Passo 4 - Execute o Projeto Spring Boot

No terminal:

```bash
./mvnw spring-boot:run
```

Ou pelo IntelliJ com botão verde na `main()`.

---

### Passo 5 - Acesse a Interface

Abra no navegador:

```
http://localhost:8080
```

Você verá a imagem da lâmpada. Ao clicar:

- Envia '1' (liga)
- Envia '2' (desliga)
- Registra a ação no banco com data/hora

---

## Estrutura do Projeto

```
projeto-arduino/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/brunodifante/projetoarduino/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       └── ProjetoArduinoApplication.java
│       └── resources/
│           ├── templates/
│           │   └── index.html
│           ├── static/
│           │   └── style.css
│           └── application.properties
```

---

## Endpoints da Aplicação

```
GET     /               -> retorna a página index.html
POST    /ligar          -> envia '1' para o Arduino
POST    /desligar       -> envia '2' para o Arduino
POST    /limpar-logs    -> deleta todos os registros do banco
```

---

## Exemplo de Log Salvo

```
ID: 1
Data/Hora: 2025-06-02 10:31:44
Ação: LIGADO

ID: 2
Data/Hora: 2025-06-02 10:32:01
Ação: DESLIGADO
```

---

## Autor

Bruno Difante  
Estudante de Ciência da Computação  
Projeto de integração web com hardware
