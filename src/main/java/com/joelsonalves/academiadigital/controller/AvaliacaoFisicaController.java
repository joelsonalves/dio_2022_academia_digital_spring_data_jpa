package com.joelsonalves.academiadigital.controller;

import com.joelsonalves.academiadigital.entity.AvaliacaoFisica;
import com.joelsonalves.academiadigital.entity.form.AvaliacaoFisicaForm;
import com.joelsonalves.academiadigital.entity.form.AvaliacaoFisicaUpdateForm;
import com.joelsonalves.academiadigital.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @GetMapping
    public List<AvaliacaoFisica> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AvaliacaoFisica getAvaliacaoFisica(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public AvaliacaoFisica create(@Valid @RequestBody AvaliacaoFisicaForm form) {
        return service.create(form);
    }

    @PutMapping("/{id}")
    public AvaliacaoFisica update(@PathVariable(name = "id") Long id, @Valid @RequestBody AvaliacaoFisicaUpdateForm formUpdate) {
        return service.update(id, formUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

}
