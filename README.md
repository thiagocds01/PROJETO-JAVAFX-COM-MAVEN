# Projeto de CRUD usando JavaFX, Java e MySQL

## Introdução

Este repositório contém o código fonte e a documentação relacionada a um projeto realizado durante meu período na faculdade. O projeto é um sistema de CRUD (Create, Read, Update, Delete) desenvolvido em Java, utilizando JavaFX para a interface gráfica e MySQL como banco de dados.

## Estrutura do Projeto

O projeto está organizado da seguinte maneira:

- **src**: Contém o código fonte do projeto.
  - **main**: Código principal da aplicação.
    - **controller**: Classe onde foi criado os metodos da aplicação.
    - **dao**: Classe onde foi configurado a conexão com banco de dados mysql.
    - **domain**: Classes onde foi implementadas as operações de sql e classe de atributos .


- **database**: Scripts SQL para a criação e inicialização do banco de dados MySQL.

- **README.md**: Este arquivo, fornecendo informações sobre o projeto.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.

- **Scene Builder**: Aplicação usada para criar a tela desktop.

- **JavaFX**: Biblioteca gráfica para a criação da interface do usuário.

- **MySQL**: Sistema de gerenciamento de banco de dados relacional.

## Configuração do Ambiente

1. **Java Development Kit (JDK)**: Certifique-se de ter o JDK instalado. O projeto foi desenvolvido usando a versão X.X.X.

2. **JavaFX**: Caso não esteja integrado ao JDK, faça a instalação separadamente.

3. **MySQL**: Instale e configure o MySQL no seu ambiente.

## Configuração do Banco de Dados

1. 

[
  CREATE TABLE USUARIO (
    id_usuario CHAR PRIMARY KEY
);

CREATE TABLE PET (
    porte CHAR,
    raca CHAR,
    sexo BOOLEAN,
    cor CHAR,
    idade DATE,
    historia_do_pet CHAR,
    id_pet CHAR,
    id_usuario CHAR,
    foto BLOB,
    PRIMARY KEY (id_pet, id_usuario)
);

]


## Executando o Projeto

1. Abra o projeto em sua IDE favorita.

2. Certifique-se de que as dependências estão configuradas corretamente.

3. Execute a aplicação.

## Funcionalidades

O sistema possui as seguintes funcionalidades básicas:

- **Cadastrar**: Adicionar novos registros ao banco de dados.

- **Consultar**: Visualizar informações existentes.

- **Atualizar**: Modificar dados existentes.

- **Excluir**: Remover registros do banco de dados.

## Conclusão

Este projeto demonstra a aplicação prática dos conceitos aprendidos durante meu curso na faculdade, especialmente na área de engenharia de requisitos de software.