package com.joelsonalves.academiadigital.controller;

import com.joelsonalves.academiadigital.entity.Matricula;
import com.joelsonalves.academiadigital.entity.form.MatriculaForm;
import com.joelsonalves.academiadigital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @GetMapping
    public List<Matricula> getAll(@RequestParam(name = "bairro", required = false) String bairro) {
        if (bairro == null) {
            return service.getAll();
        } else {
            return service.getAll(bairro);
        }
    }

    @GetMapping("/{id}")
    public Matricula getMatricula(@PathVariable(name = "id") Long id) {
        return service.get(id);
    }

    @PostMapping
    public Matricula create(@Valid @RequestBody MatriculaForm form) {
        return service.create(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {

        service.delete(id);

    }

}
