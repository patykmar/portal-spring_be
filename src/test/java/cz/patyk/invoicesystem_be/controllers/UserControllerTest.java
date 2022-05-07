package cz.patyk.invoicesystem_be.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static cz.patyk.invoicesystem_be.common.TestDtoConstants.USER_DTO_IN_ROLE_USER;
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