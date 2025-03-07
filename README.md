# Projeto de JDBC com DAO



<p>Esse projeto é um complemento do repositório "JDBC Básico" a qual o intuito é praticar o que foi ensinado em um programa
completo. Cada commit presente nesse repositório demonstra a evolução do programa. Nesse projeto utilizamos o Data Acess Object (DAO) que
seria o conceito de utilizar objetos para acessar dados, algo que foi completamente novo para mim.</p>

## Estrutura do projeto
```
src
├── application (Pasta que possui os arquivos de execução do programa (Program.java))
├── db (Pasta que possui arquivos para o "controle" da conexão com o Banco de Dados (DB.java, DbExceptio.java, DbIntegrityException.java))
├── model (Pasta de modelos do programa)
│   └── dao (Pasta de arquivos de dados de acesso ao objeto (DaoFactory.java, DeparmentDao.java, Seller.java))
|       └── impl (Pasta de arquivos de implementação de QUERY utilizando os objetos (DepartmentDaoJDBC.java, SellerDaoJDBC.java))
|   └── entities (Pasta de entitidades do programa (Department.java, Seller.java))
```

## Considerações Finais

<p>
    Queria agradecer ao professor Dr. Nélio Alves por mais um conhecimento do seu curso de Java, pois com sua didatica clara e direta 
    foi possível criar esse projeto e agregar em meu ramo profissional. Acredito que com esse projeto de hoje, criarei melhores projetos
    amanhã.
</p>