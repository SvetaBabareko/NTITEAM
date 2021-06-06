package org.babareko.NTITEAM.web;

import org.babareko.NTITEAM.model.Lord;
import org.babareko.NTITEAM.web.json.JsonUtil;
import org.babareko.NTITEAM.web.util.EntityTestNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.babareko.NTITEAM.TestData.*;
import static org.babareko.NTITEAM.TestUtil.readFromJson;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class LordControllerTest extends AbstractControllerTest {
    static final String URL = "/test/lords";

    @Autowired
    private LordController lordController;

    @Autowired
    private PlanetController planetController;

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/5"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LORD_MATCHER.contentJson(lord5));
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(LORD_MATCHER.contentJson(lordList));
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    public void getTop10() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/top10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(LORD_MATCHER.contentJson(lordListTop10));
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    public void getFreeLords() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/listFreeLords"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(LORD_MATCHER.contentJson(freeLordsList));
    }

    @Test
    public void getNotFound() {
        assertThrows(NestedServletException.class, () -> perform(MockMvcRequestBuilders.get(URL + "/26")));

        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> lordController.getById(25));
        assertEquals("Entity is not found with id : '25'", exception.getMessage());
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + "/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> lordController.getById(2));
        assertEquals("Entity is not found with id : '2'", exception.getMessage());
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NestedServletException.class, () -> perform(MockMvcRequestBuilders.delete(URL + "/25")));

        Throwable exception = assertThrows(EntityTestNotFoundException.class, () -> lordController.getById(25));
        assertEquals("Entity is not found with id : '25'", exception.getMessage());
    }

    @Test
    public void create() throws Exception {
        Lord newLord = getNewLord();
        ResultActions action = perform(MockMvcRequestBuilders.post(URL + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newLord)))
                .andExpect(status().isCreated());
        Lord created = readFromJson(action, Lord.class);
        int newId = created.getId();
        newLord.setId(newId);
        LORD_MATCHER.assertMatch(created, newLord);
        LORD_MATCHER.assertMatch(lordController.getById(newId), newLord);
    }


    @Test
    public void update() throws Exception {
        Lord updated = getUpdatedLord();
        perform(MockMvcRequestBuilders.put(URL + "/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());
        LORD_MATCHER.assertMatch(lordController.getById(5), updated);
    }


    @Test
    public void setPlanet() throws Exception {
        Lord updated = getUpdatedPlanetLord1();
        perform(MockMvcRequestBuilders.put(URL + "/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        LORD_MATCHER.assertMatch(lordController.getById(4), updated);
        PLANET_MATCHER.assertMatch(planetController.getById(22), updated.getPlanets().get(0));
        PLANET_MATCHER.assertMatch(planetController.getById(23), updated.getPlanets().get(1));
        LORD_MATCHER.assertMatch(updated, planetController.getById(22).getLord());
        LORD_MATCHER.assertMatch(updated, planetController.getById(23).getLord());
    }

}
