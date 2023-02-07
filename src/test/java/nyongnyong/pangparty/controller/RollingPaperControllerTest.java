package nyongnyong.pangparty.controller;

import nyongnyong.pangparty.controller.rollingpaper.RollingPaperController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class RollingPaperControllerTest {

    @Autowired
    RollingPaperController rollingPaperController;

    private MockMvc mock;

    @BeforeEach
    public void setMockMvc() {
        mock = MockMvcBuilders.standaloneSetup(rollingPaperController).build();
    }

    @Test
    @Transactional
    public void testGetRollingPaper() throws Exception {
        System.out.println("=========START TEST ROLLING PAPER=========");
        mock.perform(get("/events/7/rollingpaper")).andExpect(status().isOk());
        System.out.println("=========END TEST ROLLING PAPER=========");
    }


    @Test
    @Transactional
    public void testGetRollingPaperSticker() throws Exception {
        System.out.println("=========START TEST ROLLING PAPER STICKER=========");
        mock.perform(get("/events/7/rollingpaper/1/stickers?topStart=0&topEnd=100")).andExpect(status().isOk());
        System.out.println("=========END TEST ROLLING PAPER STICKER=========");
    }

}
