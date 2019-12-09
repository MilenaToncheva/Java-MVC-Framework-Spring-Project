package softuni.artgallery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.web.base.ControllerTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AccessControllerTest extends ControllerTestBase {

    @Test
    @WithMockUser("morgana")
    public void unauthorized_shouldReturnCorrectView() throws Exception {

        this.mockMvc
                .perform(get("/unauthorized"))
                .andExpect(view().name("unauthorized"));
    }
}
