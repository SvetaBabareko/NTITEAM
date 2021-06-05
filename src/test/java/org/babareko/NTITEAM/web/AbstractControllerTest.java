package org.babareko.NTITEAM.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
//@Transactional
@AutoConfigureMockMvc
//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
public class AbstractControllerTest {
    @Autowired
    protected MockMvc mockMvc;

   protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
   }
}
