package cz.patyk.invoicesystem_be.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AddressController.class)
class AddressControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAddresses() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/addresses");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals("Hello, World", mvcResult.getResponse().getContentAsString());
    }
}