package com.javarush.jira.profile.internal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.mail.MailService;
import com.javarush.jira.profile.ProfileTo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@ActiveProfiles("test")
//@RequiredArgsConstructor
//@AutoConfigureMockMvc
//class ProfileRestControllerTest extends AbstractControllerTest {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private static final String USER_MAIL = "user@gmail.com";
//    private static final String GUEST_MAIL = "guest@gmail.com";
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void getUserProfile() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(ProfileTestData.USER_PROFILE_TO), true));
//    }
//
//    @Test
//    @WithUserDetails(GUEST_MAIL)
//    void getGuestProfile() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(ProfileTestData.GUEST_PROFILE_EMPTY_TO), true));
//    }
//
//    @Test
//    @WithAnonymousUser
//    void getUnauthorized() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void updateSuccess() throws Exception {
//        ProfileTo updatedProfile = ProfileTestData.getUpdatedTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedProfile)))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void updateInvalidData() throws Exception {
//        ProfileTo invalidProfile = ProfileTestData.getInvalidTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(invalidProfile)))
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void updateUnknownNotification() throws Exception {
//        ProfileTo unknownNotification = ProfileTestData.getWithUnknownNotificationTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(unknownNotification)))
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void updateUnknownContact() throws Exception {
//        ProfileTo unknownContact = ProfileTestData.getWithUnknownContactTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(unknownContact)))
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    @WithUserDetails(USER_MAIL)
//    void updateContactHtmlUnsafe() throws Exception {
//        ProfileTo htmlUnsafeContact = ProfileTestData.getWithContactHtmlUnsafeTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(htmlUnsafeContact)))
//                .andExpect(status().isUnprocessableEntity());
//    }
//
//    @Test
//    @WithAnonymousUser
//    void updateUnauthorized() throws Exception {
//        ProfileTo updatedProfile = ProfileTestData.getUpdatedTo();
//        perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updatedProfile)))
//                .andExpect(status().isUnauthorized());
//    }
//}



import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.ProfileTo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
@AutoConfigureMockMvc
class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    private static final String USER_MAIL = "user@gmail.com";
    private static final String GUEST_MAIL = "guest@gmail.com";


    @MockBean
    private MailService mailService;

    // Успешное получение профиля для user
    @Test
    @WithUserDetails(USER_MAIL)
    void getUserProfile() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ProfileTestData.USER_PROFILE_TO), true));
    }

    // Успешное получение профиля для guest
    @Test
    @WithUserDetails(GUEST_MAIL)
    void getGuestProfile() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(ProfileTestData.GUEST_PROFILE_EMPTY_TO), true));
    }

    // Неавторизованный пользователь получает 401
    @Test
    @WithAnonymousUser
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    // Успешное обновление профиля

    @Test
    @WithUserDetails(USER_MAIL)
    void updateSuccess() throws Exception {
        ProfileTo updatedProfile = ProfileTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProfile)))
                .andExpect(status().isNoContent());
    }

    // Невалидные данные — ожидаем 422
    @Test
    @WithUserDetails(USER_MAIL)
    void updateInvalidData() throws Exception {
        ProfileTo invalidProfile = ProfileTestData.getInvalidTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidProfile)))
                .andExpect(status().isUnprocessableEntity());
    }

    // Неизвестное уведомление — 422
    @Test
    @WithUserDetails(USER_MAIL)
    void updateUnknownNotification() throws Exception {
        ProfileTo unknownNotification = ProfileTestData.getWithUnknownNotificationTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unknownNotification)))
                .andExpect(status().isUnprocessableEntity());
    }

    // Неизвестный тип контакта — 422
    @Test
    @WithUserDetails(USER_MAIL)
    void updateUnknownContact() throws Exception {
        ProfileTo unknownContact = ProfileTestData.getWithUnknownContactTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unknownContact)))
                .andExpect(status().isUnprocessableEntity());
    }

    // HTML в контактах — опасно, 422
    @Test
    @WithUserDetails(USER_MAIL)
    void updateContactHtmlUnsafe() throws Exception {
        ProfileTo htmlUnsafeContact = ProfileTestData.getWithContactHtmlUnsafeTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(htmlUnsafeContact)))
                .andExpect(status().isUnprocessableEntity());
    }

    // Неавторизованный пользователь пытается обновить профиль — 401
    @Test
    @WithAnonymousUser
    void updateUnauthorized() throws Exception {
        ProfileTo updatedProfile = ProfileTestData.getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProfile)))
                .andExpect(status().isUnauthorized());
    }
}