package edu.udacity.java.nano;

import edu.udacity.java.nano.WebSocketChatApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatApplication.class)
public class TestChatServer {

    @Autowired
    private MockMvc mvc;

    @Test
    public void login() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/").accept(MediaType.APPLICATION_JSON));

    }

    @Test
    public void userJoin() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/login/{id}", 1).accept(MediaType.APPLICATION_JSON));
    }


}
