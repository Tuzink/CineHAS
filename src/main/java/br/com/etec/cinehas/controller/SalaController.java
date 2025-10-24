package br.com.etec.cinehas.controller;


import br.com.etec.cinehas.entity.Sala;
import br.com.etec.cinehas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController
{
    @Autowired
    private SalaRepository repository;

    @GetMapping
    public List<Sala> listar() {
    return repository.findAll();
    }

    @GetMapping({"/id"})
    public Optional<Sala> buscarPorId(@PathVariable("id")Integer id)
    {
        var sala = repository.findById(id);
        if (sala.isPresent())
            return sala;
        return null;
    }

    public Sala cadrastar (@RequestBody Sala sala)
    {
        return repository.save(sala);
    }

    @PutMapping({"/id"})
    public Sala alterar(@RequestBody Sala sala, @PathVariable Integer id)
    {
        if(id == sala.getId() && buscarPorId(id).isPresent()){
            return repository.save(sala);
            }
        return null;

    }
}

