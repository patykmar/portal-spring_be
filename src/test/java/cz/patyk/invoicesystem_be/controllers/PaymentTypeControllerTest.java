package cz.patyk.invoicesystem_be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.patyk.invoicesystem_be.dto.in.PaymentTypeDtoIn;
import cz.patyk.invoicesystem_be.entities.PaymentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestConstants.PAYMENT_TYPE_TEST_NAME;
import static cz.patyk.invoicesystem_be.common.TestEntityConstants.PAYMENT_TYPE_ENTITY;
import static cz.patyk.invoicesystem_be.common.TestEntityConstants.PAYMENT_TYPE_ENTITY_EDIT;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;

//@DataJpaTest
@Slf4j
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
    @DisplayName("Testing API HTTP methods POST, PUT and DELETE")
    void apiTest() throws Exception {
        String paymentTypeEntityString = objectMapper.writeValueAsString(PAYMENT_TYPE_ENTITY);

        log.info("==== Testing post method: creating new PaymentType ====");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paymentTypeEntityString))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(PAYMENT_TYPE_TEST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").isBoolean())
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").value(PAYMENT_TYPE_ENTITY.isDefault()))
                .andReturn();

        PaymentType paymentType = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                PaymentType.class
        );

        log.info("==== Testing put method: editing PaymentType with id: {} ====", paymentType.getId());

        String paymentTypeEntityEditString = objectMapper.writeValueAsString(PAYMENT_TYPE_ENTITY_EDIT);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(URL + "/" + paymentType.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(paymentTypeEntityEditString)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(paymentType.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(PAYMENT_TYPE_ENTITY_EDIT.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").isBoolean())
                .andExpect(MockMvcResultMatchers.jsonPath("$.default").value(PAYMENT_TYPE_ENTITY_EDIT.isDefault()))
        ;

        log.info("==== Testing delete method: Delete PaymentType with id: {} ====", paymentType.getId());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(URL + "/" + paymentType.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
        ;

    }

    private static Stream<Arguments> wrongDtoInProvider() {
        return Stream.of(
                Arguments.of(PaymentTypeDtoIn.builder().name(null).isDefault(true).build()),
                Arguments.of(PaymentTypeDtoIn.builder().name(null).isDefault(false).build())
        );
    }

    @ParameterizedTest
    @MethodSource("wrongDtoInProvider")
    void newItemExpectFail(PaymentTypeDtoIn paymentTypeDtoIn) throws Exception {
        String paymentTypeEntityString = objectMapper.writeValueAsString(paymentTypeDtoIn);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(paymentTypeEntityString))

                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIds")
    void deleteInvalidId(Long id) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(URL + "/" + id)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIds")
    void editInvalidId(Long id) throws Exception {
        String paymentTypeEntityEditString = objectMapper.writeValueAsString(PAYMENT_TYPE_ENTITY_EDIT);

        mockMvc.perform(
                        MockMvcRequestBuilders.put(URL + "/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(paymentTypeEntityEditString)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;

    }

    private static Stream<Arguments> provideInvalidIds() {
        return Stream.of(
                Arguments.of(Long.MIN_VALUE),
                Arguments.of(LONG_ZERO),
                Arguments.of(LONG_MINUS_ONE)
        );
    }
}