package com.joelsonalves.academiadigital.service.impl;

import com.joelsonalves.academiadigital.entity.Aluno;
import com.joelsonalves.academiadigital.entity.AvaliacaoFisica;
import com.joelsonalves.academiadigital.entity.Matricula;
import com.joelsonalves.academiadigital.entity.form.AlunoForm;
import com.joelsonalves.academiadigital.entity.form.AlunoUpdateForm;
import com.joelsonalves.academiadigital.repository.AlunoRepository;
import com.joelsonalves.academiadigital.repository.AvaliacaoFisicaRepository;
import com.joelsonalves.academiadigital.repository.MatriculaRepository;
import com.joelsonalves.academiadigital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Override
    public Aluno create(AlunoForm form) {

        Aluno aluno = null;

        if (alunoRepository.findByCpf(form.getCpf()).size() == 0) {

            aluno = new Aluno();
            aluno.setNome(form.getNome());
            aluno.setCpf(form.getCpf());
            aluno.setBairro(form.getBairro());
            aluno.setDataDeNascimento(form.getDataDeNascimento());
            aluno = alunoRepository.save(aluno);

        }

        return aluno;
    }

    @Override
    public Aluno get(Long id) {
        Aluno aluno = null;
        if (alunoRepository.existsById(id)) {
            aluno = alunoRepository.findById(id).get();
        }
        return aluno;
    }

    @Override
    public List<Aluno> getAll() {

        return alunoRepository.findAll();

    }

    @Override
    public List<Aluno> getAll(LocalDate dataDeNascimento) {

        return alunoRepository.findByDataDeNascimento(dataDeNascimento);

    }

    @Override
    public List<Aluno> getAll(String bairro) {

        return alunoRepository.findByBairro(bairro);

    }

    @Override
    public List<Aluno> getAll(String bairro, LocalDate dataNascimento) {
        return alunoRepository.findByBairroAndDataDeNascimento(bairro, dataNascimento);
    }

    @Override
    public Aluno update(Long id, AlunoUpdateForm formUpdate) {
        Aluno aluno = null;
        if (alunoRepository.existsById(id)) {
            aluno = alunoRepository.findById(id).get();
            aluno.setNome(formUpdate.getNome());
            aluno.setBairro(formUpdate.getBairro());
            aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
            aluno = alunoRepository.save(aluno);
        }
        return aluno;
    }

    @Override
    public void delete(Long id) {

        if (alunoRepository.existsById(id)) {

            Aluno aluno = alunoRepository.findById(id).get();

            List<AvaliacaoFisica> avaliacoesFisicas = avaliacaoFisicaRepository.findByAluno(aluno);
            if (avaliacoesFisicas != null) {
                avaliacaoFisicaRepository.deleteAllInBatch(avaliacoesFisicas);
            }
            List<Matricula> matriculas = matriculaRepository.findByAluno(aluno);
            if (matriculas != null) {
                matriculaRepository.deleteAllInBatch(matriculas);
            }

            List<Aluno> alunos = new ArrayList();
            alunos.add(aluno);
            alunoRepository.deleteAllInBatch(alunos);
        }

    }

    @Override
    public List<AvaliacaoFisica> getAvaliacaoFisicaByAlunoId(Long id) {
        List<AvaliacaoFisica> avaliacoesFisicas = null;

        if (alunoRepository.existsById(id)) {
            Aluno aluno = alunoRepository.findById(id).get();
            avaliacoesFisicas = aluno.getAvaliacoes();
        }

        return avaliacoesFisicas;
    }

}
