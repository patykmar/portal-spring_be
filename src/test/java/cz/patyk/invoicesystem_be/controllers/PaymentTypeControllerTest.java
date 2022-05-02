package cz.patyk.invoicesystem_be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_ENTITY;
import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_TEST_NAME;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentTypeControllerTest {
    private static final String URL = "/payment-types";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    //https://www.baeldung.com/spring-boot-bean-validation

    @Test
    void newItem() throws Exception {
        String paymentTypeEntityString = objectMapper.writeValueAsString(PAYMENT_TYPE_ENTITY);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paymentTypeEntityString))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(PAYMENT_TYPE_TEST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").isBoolean())
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").value(PAYMENT_TYPE_ENTITY.isDefault()))
        ;
    }

    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE));

    }
}