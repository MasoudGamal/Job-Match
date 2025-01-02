package org.springdemo.serviceproviders.factor.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdemo.serviceproviders.factor.dto.FactorRequest;
import org.springdemo.serviceproviders.factor.dto.FactorResponse;
import org.springdemo.serviceproviders.factor.service.FactorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/factor")
@RequiredArgsConstructor
public class FactorController {

    private final FactorService factorService;

    @PostMapping
    public FactorResponse create(@Valid @RequestBody FactorRequest factorRequest){
        return factorService.create(factorRequest);
    }

    @GetMapping("/{id}")
    public FactorResponse findById(@PathVariable Integer id){
        return factorService.findById(id);
    }

    @GetMapping
    public List<FactorResponse> FindAll(){
        return factorService.FindAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        factorService.delete(id);
    }

    @PutMapping
    public FactorResponse update(@Valid @RequestBody FactorRequest factorRequest){

        return factorService.update(factorRequest);

    }
}
