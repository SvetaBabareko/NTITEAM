package org.babareko.NTITEAM.web;


import org.babareko.NTITEAM.TestData;
import org.babareko.NTITEAM.model.Planet;
import org.babareko.NTITEAM.repository.PlanetRepository;
import org.babareko.NTITEAM.web.json.JsonUtil;
import org.babareko.NTITEAM.web.util.EntityTestNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.babareko.NTITEAM.TestData.*;
import static org.babareko.NTITEAM.TestUtil.readFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        perform(MockMvcRequestBuilders.get(URL + "/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(PLANET_MATCHER.contentJson(TestData.planetList));
    }


    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/24"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(PLANET_MATCHER.contentJson(planet9));
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NestedServletException.class, () -> perform(MockMvcRequestBuilders.get(URL + "/2")));

        // perform(MockMvcRequestBuilders.get(URL + "/2"))
        //        .andExpect(status().is5xxServerError()).andDo(print());
// .andExpect(status().reason(containsString("Bad credentials")))
//                .andExpect(unauthenticated());
        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> planetController.getById(2));
        assertEquals("Entity is not found with id : '2'", exception.getMessage());
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + "/25"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> planetController.getById(25));
        assertEquals("Entity is not found with id : '25'", exception.getMessage());
    }

    @Test
    public void deleteNotFound() throws Exception{
        assertThrows(NestedServletException.class, () -> perform(MockMvcRequestBuilders.delete(URL + "/2")));

        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> planetController.getById(2));
        assertEquals("Entity is not found with id : '2'", exception.getMessage());
    }

    @Test
    public void create() throws Exception {
        Planet newPlanet = getNewPlanet();
        ResultActions action = perform(MockMvcRequestBuilders.post(URL+"/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newPlanet)))
                .andExpect(status().isCreated());
        Planet created = readFromJson(action, Planet.class);
        int newId = created.getId();
        newPlanet.setId(newId);

        PLANET_MATCHER.assertMatch(created, newPlanet);
        PLANET_MATCHER.assertMatch(planetController.getById(newId), newPlanet);
    }

    @Test
    public void update() throws Exception {
        Planet updated = getUpdatedPlanet();
        perform(MockMvcRequestBuilders.put(URL + "/24")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        PLANET_MATCHER.assertMatch(planetController.getById(24), updated);
    }


}

