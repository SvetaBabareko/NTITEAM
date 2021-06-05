package org.babareko.NTITEAM.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.babareko.NTITEAM.repository.LordRepository;
import org.babareko.NTITEAM.repository.PlanetRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = LordController.URL)
@Slf4j
public class LordController {
    static final String URL = "/test/lords";

    private final LordRepository lordRepository;

}
