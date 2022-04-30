package com.joelsonalves.academiadigital.repository;

import com.joelsonalves.academiadigital.entity.Aluno;
import com.joelsonalves.academiadigital.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

  /**
   *
   * @param bairro bairro referência para o filtro
   * @return lista de alunos matriculados que residem no bairro passado como parâmetro
   */
  List<Matricula> findByAlunoBairro(String bairro);

  List<Matricula> findByAluno(Aluno aluno);

}
