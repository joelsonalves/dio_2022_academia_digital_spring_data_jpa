package com.joelsonalves.academiadigital.controller;

import com.joelsonalves.academiadigital.entity.Aluno;
import com.joelsonalves.academiadigital.entity.AvaliacaoFisica;
import com.joelsonalves.academiadigital.entity.form.AlunoForm;
import com.joelsonalves.academiadigital.entity.form.AlunoUpdateForm;
import com.joelsonalves.academiadigital.infra.utils.JavaTimeUtils;
import com.joelsonalves.academiadigital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @GetMapping
    public List<Aluno> getAll(
            @RequestParam(value = "bairro", required = false) String bairro,
            @RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento) {

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
        } catch(Exception e) {
            localDate = null;
        }

        if (bairro == null && localDate == null) {
            return service.getAll();
        } else if (localDate == null) {
            return service.getAll(bairro);
        } else if (bairro == null) {
            return service.getAll(localDate);
        } else {
            return service.getAll(bairro, localDate);
        }

    }

    @GetMapping("/{id}")
    public Aluno getAluno(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public Aluno create(@Valid @RequestBody AlunoForm form) {

        return service.create(form);

    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable(name = "id") Long id, @Valid @RequestBody AlunoUpdateForm formUpdate) {
        return service.update(id, formUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @GetMapping("/avaliacoes/{id}")
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaByAlunoId(@PathVariable Long id) {
        return service.getAvaliacaoFisicaByAlunoId(id);
    }

}
