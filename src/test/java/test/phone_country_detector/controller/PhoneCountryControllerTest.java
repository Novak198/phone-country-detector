package test.phone_country_detector.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import test.phone_country_detector.service.PhoneCountryService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneCountryControllerTest {
    @Mock
    private PhoneCountryService phoneCountryService;

    @InjectMocks
    private PhoneCountryController phoneCountryController;

    @Test
    void detectCountry_shouldReturnCountry_whenPhoneNumberIsValid() {
        when(phoneCountryService.detectCountry("+79161234567")).thenReturn("Russia");

        ResponseEntity<Map<String, String>> response = phoneCountryController.detectCountry("+79161234567");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Russia", response.getBody().get("country"));
    }

    @Test
    void detectCountry_shouldReturnNotFound_whenCountryIsUnknown() {
        when(phoneCountryService.detectCountry("+123456"))
                .thenReturn("Unknown country");

        ResponseEntity<Map<String, String>> response = phoneCountryController.detectCountry("+123456");

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Страна не найдена", response.getBody().get("error"));
    }

    @Test
    void detectCountry_shouldReturnBadRequest_whenPhoneNumberIsEmpty() {
        ResponseEntity<Map<String, String>> response = phoneCountryController.detectCountry("");

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Требуется номер телефона", response.getBody().get("error"));
    }
}
