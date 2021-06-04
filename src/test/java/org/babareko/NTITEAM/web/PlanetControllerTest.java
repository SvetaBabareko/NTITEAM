package org.babareko.NTITEAM.web;

import org.babareko.NTITEAM.repository.PlanetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlanetControllerTest extends AbstractControllerTest{
    static final String URL = "/test/planets/";

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    void findAll() throws Exception {
   /*     perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect()));*/

    }

}
