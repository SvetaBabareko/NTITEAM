package org.babareko.NTITEAM.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babareko.NTITEAM.model.Lord;
import org.babareko.NTITEAM.repository.LordRepository;
import org.babareko.NTITEAM.web.util.EntityTestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity deleteById(@PathVariable int id) throws EntityTestNotFoundException {
        log.info("delete lord {}", id);
        Lord lord = lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        lordRepository.delete(lord);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Lord> getAll() {
        log.info("getAll");
        return lordRepository.findAll();
    }

    @PostMapping()
    public Lord create(@Valid @RequestBody Lord lord) {
        return lordRepository.save(lord);
    }

    // Получить запись по id
    @GetMapping("/{id}")
    public Lord getById(@PathVariable(value = "id") Integer id) throws EntityTestNotFoundException {
        return lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
    }

    // Обновить запись
    @PutMapping("/{id}")
    public Lord update(@PathVariable(value = "id") Integer id,
                             @Valid @RequestBody Lord lordNew) throws EntityTestNotFoundException {

        Lord lord = lordRepository.findById(id)
                .orElseThrow(() -> new EntityTestNotFoundException(id));
        lord.setName(lordNew.getName());
        lord.setAge(lordNew.getAge());

        Lord lordUpdate = lordRepository.save(lord);
        return lordUpdate;
    }


}
