package org.babareko.NTITEAM.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babareko.NTITEAM.model.Planet;
import org.babareko.NTITEAM.repository.PlanetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = PlanetController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PlanetController {
    static final String URL = "/test/planets";

    private final PlanetRepository planetRepository;

    @DeleteMapping("/test/planets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        log.info("delete {}", id);
        planetRepository.deleteById(id);
    }

    @GetMapping("/")
    public List<Planet> getAll() {
        log.info("getAll");
        return planetRepository.findAll();
    }





}
