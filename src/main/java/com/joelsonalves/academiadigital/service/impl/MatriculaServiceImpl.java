package com.joelsonalves.academiadigital.service.impl;

import com.joelsonalves.academiadigital.entity.Aluno;
import com.joelsonalves.academiadigital.entity.Matricula;
import com.joelsonalves.academiadigital.entity.form.MatriculaForm;
import com.joelsonalves.academiadigital.repository.AlunoRepository;
import com.joelsonalves.academiadigital.repository.MatriculaRepository;
import com.joelsonalves.academiadigital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Matricula create(MatriculaForm form) {
        Matricula matricula = new Matricula();

        if (alunoRepository.existsById(form.getAlunoId())) {

            Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();
            matricula.setAluno(aluno);
            matricula = matriculaRepository.save(matricula);

        }
        return matricula;
    }

    @Override
    public Matricula get(Long id) {
        Matricula matricula = new Matricula();
        if (matriculaRepository.existsById(id)) {
            matricula = matriculaRepository.findById(id).get();
        }
        return matricula;
    }

    @Override
    public List<Matricula> getAll() {

        return matriculaRepository.findAll();

    }

    @Override
    public List<Matricula> getAll(String bairro) {

        return matriculaRepository.findByAlunoBairro(bairro);

    }

    @Override
    public void delete(Long id) {

        if (matriculaRepository.existsById(id)) {
            Matricula matricula = matriculaRepository.findById(id).get();
            List<Matricula> matriculas = new ArrayList();
            matriculas.add(matricula);
            matriculaRepository.deleteAllInBatch(matriculas);
        }

    }
}
