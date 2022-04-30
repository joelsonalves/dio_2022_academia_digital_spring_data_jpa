package com.joelsonalves.academiadigital.service.impl;

import com.joelsonalves.academiadigital.entity.Aluno;
import com.joelsonalves.academiadigital.entity.AvaliacaoFisica;
import com.joelsonalves.academiadigital.entity.form.AvaliacaoFisicaForm;
import com.joelsonalves.academiadigital.entity.form.AvaliacaoFisicaUpdateForm;
import com.joelsonalves.academiadigital.repository.AlunoRepository;
import com.joelsonalves.academiadigital.repository.AvaliacaoFisicaRepository;
import com.joelsonalves.academiadigital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = null;
        if (alunoRepository.existsById(form.getAlunoId())) {
            Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

            avaliacaoFisica = new AvaliacaoFisica();
            avaliacaoFisica.setAluno(aluno);
            avaliacaoFisica.setPeso(form.getPeso());
            avaliacaoFisica.setAltura(form.getAltura());

            avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);
        }
        return avaliacaoFisica;
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        AvaliacaoFisica avaliacaoFisica = null;
        if (avaliacaoFisicaRepository.existsById(id)) {
            avaliacaoFisica = avaliacaoFisicaRepository.findById(id).get();
        }
        return avaliacaoFisica;
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return avaliacaoFisicaRepository.findAll();
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        AvaliacaoFisica avaliacaoFisica = null;
        if (avaliacaoFisicaRepository.existsById(id)) {
            avaliacaoFisica = avaliacaoFisicaRepository.findById(id).get();

            avaliacaoFisica.setAltura(formUpdate.getAltura());
            avaliacaoFisica.setPeso(formUpdate.getPeso());

            avaliacaoFisica = avaliacaoFisicaRepository.save(avaliacaoFisica);
        }
        return avaliacaoFisica;
    }

    @Override
    public void delete(Long id) {

        if (avaliacaoFisicaRepository.existsById(id)) {

            AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id).get();

            List<AvaliacaoFisica> avaliacoesFisicas = new ArrayList();
            avaliacoesFisicas.add(avaliacaoFisica);

            avaliacaoFisicaRepository.deleteAllInBatch(avaliacoesFisicas);

        }

    }
}
