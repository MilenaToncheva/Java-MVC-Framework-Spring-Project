package softuni.artgallery.web.base;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import softuni.artgallery.base.TestBase;


@AutoConfigureMockMvc
public class ControllerTestBase extends TestBase {
    @Autowired
    protected MockMvc mockMvc;
}
