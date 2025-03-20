package test.phone_country_detector.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.phone_country_detector.entity.CountryCode;
import test.phone_country_detector.repository.CountryCodeRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneCountryServiceTest {
    @Mock
    private CountryCodeRepository countryCodeRepository;

    @InjectMocks
    private PhoneCountryService phoneCountryService;

    @Test
    void detectCountry_shouldReturnCountry_whenPhoneNumberIsValid() {
        CountryCode countryCode = new CountryCode(1L, "Russia", "7", List.of("77", "78"));
        when(countryCodeRepository.findAllMatchingCodes("79161234567")).thenReturn(List.of(countryCode));

        String country = phoneCountryService.detectCountry("+7(916)123-45-67");
        assert country.equals("Russia");
    }

    @Test
    void detectCountry_shouldReturnUnknown_whenPhoneNumberIsInvalid() {
        when(countryCodeRepository.findAllMatchingCodes(anyString())).thenReturn(Collections.emptyList());

        String country = phoneCountryService.detectCountry("123456");
        assert country.equals("Unknown country");
    }
}
