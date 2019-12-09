package softuni.artgallery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.artgallery.web.base.ControllerTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest  extends ControllerTestBase {



    @Test
    public void index_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("home/index"));
    }

    @Test
    @WithMockUser("spring")
    public void home_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/home"))
                .andExpect(view().name("home/home"));
    }
}
