package nyongnyong.pangparty.controller;

import nyongnyong.pangparty.controller.auth.MemberAuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberAuthControllerTest {

    @Autowired
    MemberAuthController memberAuthController;

    private MockMvc mockMvc;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberAuthController).build();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @Transactional
    public void testLoginLogout() throws Exception {
        System.out.println("=========START testLoginLogout=========");
//        request.setParameter("username", "testuser");
//        request.setParameter("password", "password");
//
//        MockHttpServletRequestBuilder req = post("/account/login")
//                .requestAttr("email", "pang3333@pang.party")
//                .requestAttr("password", "asdf1234");
//        mockMvc.perform(req)
//                .andExpect(status().isOk());
//
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        assertNotNull(principal);
//
//        SecurityContextHolder.clearContext();


        System.out.println("=========END testLoginLogout=========");
    }

}
