package test.phone_country_detector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.phone_country_detector.entity.CountryCode;
import test.phone_country_detector.repository.CountryCodeRepository;

import java.util.List;

/**
 * Сервис бизнес логики отвечающий ща поиск соответствий в базе данных.
 */

@Service
public class PhoneCountryService {

    private final CountryCodeRepository countryCodeRepository;

    @Autowired
    public PhoneCountryService(CountryCodeRepository countryCodeRepository) {
        this.countryCodeRepository = countryCodeRepository;
    }

    @Transactional(readOnly = true)
    public String detectCountry(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

        List<CountryCode> matchingCodes = countryCodeRepository.findAllMatchingCodes(phoneNumber);

        if (!matchingCodes.isEmpty()) {
            return matchingCodes.get(0).getCountry();
        } else {
            return "Unknown country";
        }
    }
}