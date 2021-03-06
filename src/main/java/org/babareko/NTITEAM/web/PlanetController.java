package org.babareko.NTITEAM.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babareko.NTITEAM.model.Planet;
import org.babareko.NTITEAM.repository.PlanetRepository;
import org.babareko.NTITEAM.web.util.EntityTestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = PlanetController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PlanetController {
    static final String URL = "/test/planets";

    private final PlanetRepository planetRepository;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws EntityTestNotFoundException {
        log.info("delete planet {}", id);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        planetRepository.delete(planet);
    }

    @GetMapping
    public List<Planet> getAll() {
        log.info("get all planets");
        return planetRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet) {
        log.info("create planet {}", planet);
        Planet created = planetRepository.save(planet);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}")
    public Planet getById(@PathVariable(value = "id") Integer id) throws EntityTestNotFoundException {
        log.info("get planet {}", id);
        return planetRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Planet update(@PathVariable(value = "id") Integer id,
                         @Valid @RequestBody Planet planetNew) throws EntityTestNotFoundException {
        log.info("update planet {}: {}", id, planetNew);
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        planet.setName(planetNew.getName());
        planet.setLord(planetNew.getLord());
        return planetRepository.save(planet);
    }
}
