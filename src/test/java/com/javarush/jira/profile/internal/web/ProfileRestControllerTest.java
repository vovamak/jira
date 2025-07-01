package com.javarush.jira.profile.internal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.jira.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.profile.internal.web.ProfileTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String REST_URL = ProfileRestController.REST_URL;
    private static final String USER_MAIL = "user@gmail.com";
    private static final String GUEST_MAIL = "guest@gmail.com";

    @Test
    @WithUserDetails(USER_MAIL)
    void getProfile_AuthenticatedUser_ShouldReturnProfile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mailNotifications").isArray())
                .andExpect(jsonPath("$.contacts").isArray())
                .andDo(print());
    }
    @Test
    void getProfile_UnauthenticatedUser_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile_ValidData_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getUpdatedTo())))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithUserDetails(GUEST_MAIL)
    void updateProfile_EmptyProfile_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GUEST_PROFILE_EMPTY_TO)))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile_InvalidData_ShouldReturnUnprocessableEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getInvalidTo())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile_UnknownNotification_ShouldReturnUnprocessableEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getWithUnknownNotificationTo())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile_UnknownContact_ShouldReturnUnprocessableEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getWithUnknownContactTo())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @WithUserDetails(USER_MAIL)
    void updateProfile_HtmlUnsafeContact_ShouldReturnUnprocessableEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getWithContactHtmlUnsafeTo())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void updateProfile_UnauthenticatedUser_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_PROFILE_TO)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}