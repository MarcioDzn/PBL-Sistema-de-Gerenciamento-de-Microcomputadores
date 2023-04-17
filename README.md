<h1 align="center"> Sistema de Gerenciamento de Microcomputadores </h1>
<p>Projeto de Sistema para Gerenciamento e Manutenção de Microcomputadores para a disciplina EXA863 - MI - Programação II da UEFS - Univerdade Estadual de Feira de Santana</p>

<div align="center">
  
  <a href=""> ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) </a>

</div>


## 📖 Descrição do projeto

Sistema para controlar e gerenciar a execução de serviços relacionados à assistência técnica de microcomputadores.


## 📋 Funcionalidades

### Registro de clientes
O sistema deve ser capaz de armazenar as informações dos clientes que precisam de assistência técnica. Isso inclui nome, endereço, número de telefone e outras informações relevantes.

### Gerenciamento de ordens de serviço
O sistema deve permitir que o técnico de assistência técnica crie e gerencie ordens de serviço para cada cliente. Os serviços devem ser:
 - Montagem/Instalação: RAM (R$ 20 por peça), placa mãe (R$ 100), fonte (R$
30), placa de vídeo (R$100), HD/SSD (R$30), outros (descrição) (valor a
definir);
 - Formatação e instalação de sistemas: Sistema operacional (R$ 50) e
Programas (R$10);
 - Limpeza: limpeza (R$70)

### Agenda de atendimento
Os serviços devem ser realizados em ordem de chegada. Assim, o sistema deve permitir que o técnico da assistência técnica atualize cada ordem de serviço com o status atual (em andamento, finalizado ou cancelado). Ao finalizar, deverá constar a descrição do serviço e o valor total. Ao abrir o sistema deve conter na página inicial todas as ordens de serviços em aberto do dia. Não é possível o mesmo técnico iniciar outra ordem de serviço sem finalizar a anterior.

### Gerenciamento de peças e estoque
O sistema permite que o técnico de assistência técnica gerencie o estoque de peças e equipamentos necessários para realizar os serviços. Isso inclui o controle do estoque, a criação de ordens de compra para reabastecimento de estoque e o controle de custos.

### Faturamento e pagamento
O sistema deve permitir que o técnico de assistência técnica gere faturas para os serviços prestados, bem como aceite pagamentos de várias formas, como cartões de crédito, transferências bancárias ou em dinheiro.

### Relatórios
O sistema deve ser capaz de gerar relatórios sobre o desempenho do serviço, incluindo tempo médio de espera, custos de peças e estoque, bem como satisfação do cliente.

## 📊 Diagrama de Classes
![Diagrama de Classes do projeto](https://github.com/MarcioDzn/PBL-Sistema-de-Gerenciamento-de-Microcomputadores/blob/main/Diagrama%20de%20Classes%20PBL.png)

## 🚀 Instalação
Clone o repositório:
```bash
$ git clone https://github.com/MarcioDzn/PBL-Sistema-de-Gerenciamento-de-Microcomputadores.git
```

Abra o projeto no IntelliJ IDEA.
