package ru.bootcamp.banksystem.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBalanceFromRightAccountId() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/rest/v1/accounts/1/balance"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBalanceFromIllegalAccountId() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/rest/v1/accounts/100/balance"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBalanceFromNullAccountId() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/rest/v1/accounts/0/balance"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCardsByAccountId() throws Exception {


        this.mockMvc.perform(get("http://localhost:8080/rest/v1/accounts/1/cards"))
                .andDo(print())
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"number\": \"1000100010001001\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 2,\n" +
                        "    \"number\": \"1000100010001002\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 3,\n" +
                        "    \"number\": \"1000100010001003\"\n" +
                        "  }\n" +
                        "]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getNewCardWithIllegalAccountId() throws Exception {

        this.mockMvc.perform(post("http://localhost:8080/rest/v1/accounts/100/cards"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //@Test
    public void DepositFundsWithRightAccount() throws Exception {

        this.mockMvc.perform(put("http://localhost:8080/rest/v1/accounts/0/balance")
                .contentType(MediaType.APPLICATION_JSON)
                .content("amount:10000"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(""));
    }

    // @Test
    public void DepositFundsToNonExistentAccount() throws Exception {

        this.mockMvc.perform(put("http://localhost:8080/rest/v1/accounts/0/balance"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json(""));
    }


}
