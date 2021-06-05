package org.babareko.NTITEAM.web;


import org.babareko.NTITEAM.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PlanetControllerTest extends AbstractControllerTest {
    static final String URL = "/test/planets";

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE));
              //  .andExpect(PLANET_MATCHER.contentJson(TestData.planetList));
    }

}