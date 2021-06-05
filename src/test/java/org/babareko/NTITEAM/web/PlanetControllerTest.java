package org.babareko.NTITEAM.web;


import org.babareko.NTITEAM.TestData;
import org.babareko.NTITEAM.repository.PlanetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.babareko.NTITEAM.TestData.PLANET_MATCHER;
import static org.babareko.NTITEAM.TestData.planet10;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PlanetControllerTest extends AbstractControllerTest {
    static final String URL = "/test/planets";

    @Autowired
    private PlanetController planetController;

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL+"/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(PLANET_MATCHER.contentJson(TestData.planetList));
    }


    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL+"/25"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PLANET_MATCHER.contentJson(planet10));
   }

}

