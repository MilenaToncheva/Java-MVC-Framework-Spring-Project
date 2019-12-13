package softuni.artgallery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import softuni.artgallery.services.services.ArtistService;
import softuni.artgallery.services.services.CloudinaryService;
import softuni.artgallery.web.base.ControllerTestBase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class ArtistControllerTest extends ControllerTestBase {

    @MockBean
    ArtistService artistService;
    @MockBean
    CloudinaryService cloudinaryService;

   //@Test
   //@WithMockUser(username = "morgana", roles = {"ROLE_MODERATOR"},password = "1aA#111")
   //void create_returnCorrectView() throws Exception {
   //    mockMvc
   //            .perform(get("/artists/create"))
   //            .andExpect(view().name("artists/artist-create"));
   //}



}
