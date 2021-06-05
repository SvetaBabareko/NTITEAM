package org.babareko.NTITEAM.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.babareko.NTITEAM.TestData.lord5;

import static org.babareko.NTITEAM.TestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class LordControllerTest extends AbstractControllerTest{
    static final String URL = "/test/lords";

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL+"/5"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LORD_MATCHER.contentJson(lord5));
    }
}
