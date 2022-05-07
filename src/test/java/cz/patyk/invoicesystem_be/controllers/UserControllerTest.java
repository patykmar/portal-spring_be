package cz.patyk.invoicesystem_be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.patyk.invoicesystem_be.dto.out.UserDtoOut;
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

import static cz.patyk.invoicesystem_be.common.TestDtoConstants.USER_DTO_IN_ROLE_USER;
import static cz.patyk.invoicesystem_be.common.TestDtoConstants.USER_DTO_IN_ROLE_USER_WITH_PASSWORD;
import static org.apache.commons.lang3.math.NumberUtils.LONG_MINUS_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String URL = "/users";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE));
    }

    @Test
    @DisplayName("Testing API HTTP methods POST, PUT and DELETE")
    void testApi() throws Exception {
        String userDtoInRoleUserWithPassword = objectMapper.writeValueAsString(USER_DTO_IN_ROLE_USER_WITH_PASSWORD);

        log.info("==== Testing post method: creating new User ====");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoInRoleUserWithPassword))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaTypes.HAL_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeOfCompanyId").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeOfCompanyId").value(USER_DTO_IN_ROLE_USER_WITH_PASSWORD.getEmployeeOfCompanyId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(USER_DTO_IN_ROLE_USER_WITH_PASSWORD.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(USER_DTO_IN_ROLE_USER_WITH_PASSWORD.getFirstName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(USER_DTO_IN_ROLE_USER_WITH_PASSWORD.getLastName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastLogin").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdDate").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordChanged").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").value(USER_DTO_IN_ROLE_USER_WITH_PASSWORD.getRoles()))
                .andReturn();

        UserDtoOut userDtoOut = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                UserDtoOut.class
        );
    }

    @Test
    void getOne() {
        assertTrue(true);
    }

    @Test
    void newItem() {
        assertTrue(true);
    }

    @Test
    void editItem() {
        assertTrue(true);
    }

    @Test
    void testEditItem() {
        assertTrue(true);
    }

    @Test
    void deleteItem() {
        assertTrue(true);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIds")
    void testGetOneWithInvalidId(Long id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIds")
    void testEditItemWithInvalidId(Long id) throws Exception {
        String userDtoInRoleUser = objectMapper.writeValueAsString(USER_DTO_IN_ROLE_USER);

        mockMvc.perform(MockMvcRequestBuilders.put(URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoInRoleUser)
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIds")
    void deleteItemWithInvalidId(Long id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private static Stream<Arguments> provideInvalidIds() {
        return Stream.of(
                Arguments.of(Long.MIN_VALUE),
                Arguments.of(LONG_ZERO),
                Arguments.of(LONG_MINUS_ONE)
        );
    }
}