package com.example.timezone.controler;


import com.example.timezone.models.EntityZone;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeZoneTests {
    private MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){

        mvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String mapToJson(Object obj)throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper objectMapper=new ObjectMapper();

        return objectMapper.readValue(json,clazz);
    }

    @Test
    public void convertZoneUTC()throws  Exception{
        String uri="/api/timezone";

        mvc.perform(post(uri)
                .content(this.mapToJson(new EntityZone("18:31:45","-3")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
