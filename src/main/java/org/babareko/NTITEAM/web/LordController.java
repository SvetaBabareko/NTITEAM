package org.babareko.NTITEAM.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babareko.NTITEAM.model.Lord;
import org.babareko.NTITEAM.model.Planet;
import org.babareko.NTITEAM.repository.LordRepository;
import org.babareko.NTITEAM.web.util.EntityTestNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = LordController.URL)
@Slf4j
public class LordController {
    static final String URL = "/test/lords";

    private final LordRepository lordRepository;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) throws EntityTestNotFoundException {
        log.info("delete lord {}", id);
        Lord lord = lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        lordRepository.delete(lord);
    }

    @GetMapping
    public List<Lord> getAll() {
        log.info("getAll lords");
        return lordRepository.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lord> create(@Valid @RequestBody Lord lord) {
        log.info("create lord");
        Lord created = lordRepository.save(lord);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Получить запись по id
    @GetMapping("/{id}")
    public Lord getById(@PathVariable(value = "id") Integer id) throws EntityTestNotFoundException {
        log.info("get lord by id");
        return lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
    }

    // Обновить запись
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Lord update(@PathVariable(value = "id") Integer id,
                             @Valid @RequestBody Lord lordNew) throws EntityTestNotFoundException {
        log.info("update lord");
        Lord lord = lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        lord.setName(lordNew.getName());
        lord.setAge(lordNew.getAge());

        Lord lordUpdate = lordRepository.save(lord);
        return lordUpdate;
    }


    @GetMapping("/top10")
    public List<Lord> getTopByAge(){
        log.info("get Top10 lords");
        return lordRepository.getTopByAge(PageRequest.of(0,10));
    }

    @GetMapping("/listFreeLords")
    public List<Lord> getAllByPlanetsIsNull(){
        log.info("get free lords");
        return lordRepository.getAllByPlanetsIsNull();
    }




}
