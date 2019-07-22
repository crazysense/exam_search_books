package myyuk.exam.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import myyuk.exam.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

        User request = new User();
        request.setUserId("user1");
        request.setUserPassword("pass1");

        ObjectMapper om = new ObjectMapper();
        MvcResult res = mvc.perform(post("/api/v1/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(om.writeValueAsString(request))).andReturn();

        System.out.println("");
    }

    @Test
    public void loginTest() throws Exception {
        User request = new User();
        request.setUserId("user1");
        request.setUserPassword("pass1");

        ObjectMapper om = new ObjectMapper();

        ResultActions a = mvc.perform(post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(om.writeValueAsString(request)));

        MvcResult ret = a.andReturn();

        System.out.println(ret);

    }
}