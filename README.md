# dio_2022_spring_data_jpa_academiadigital
 Academia Digital


## REST API Alunos

GET | http://localhost:8080/alunos | params { bairro (opicional), dataDeNascimento (opcional) } | retorna os alunos cadastrados.

GET | http://localhost:8080/alunos/{id} | retorna o aluno especificado pelo id.

GET | http://localhost:8080/alunos/avaliacoes/{id} | retorna as avaliações físicas realizadas pelo aluno especificado pelo id.

POST | http://localhost:8080/alunos | body { nome, cpf, bairro, dataDeNascimento } | cadastra um aluno.

PUT | http://localhost:8080/alunos/{id} | body { nome, bairro, dataDeNascimento } | atualiza os dados cadastrados no aluno especificado pelo id.

DELETE | http://localhost:8080/alunos/{id} | remove o aluno especificado pelo id.


## REST API Avaliação Física

GET | http://localhost:8080/avaliacoes | retorna as avaliações físicas cadastradas.

GET | http://localhost:8080/avaliacoes/{id} | retorna a avaliação física especificada pelo id.

POST | http://localhost:8080/avaliacoes | body { alunoId, altura, peso } | cadastra uma avaliação física.

PUT | http://localhost:8080/avaliacoes/{id} | body { altura, peso } | atualiza os dados cadastrados na avaliação física especificada pelo id.

DELETE | http://localhost:8080/avaliacoes/{id} | remove a avaliação física especificada pelo id.


## REST API Matrícula

GET | http://localhost:8080/matriculas | retorna as matrículas cadastradas.

GET | http://localhost:8080/matriculas/{id} | retorna a matrícula especificada pelo id.

POST | http://localhost:8080/matriculas | body { alunoId } | matricula um aluno.

DELETE | http://localhost:8080/matriculas/{id} | remove a matrícula especificada pelo id.


## Referência

https://github.com/cami-la/academia-digital


## Nota

Para executar utilize "mvn spring-boot:run".