package test.phone_country_detector.parser;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.phone_country_detector.entity.CountryCode;
import test.phone_country_detector.repository.CountryCodeRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Модуль отвечающий за загрузку данных в БД из файла - country_codes.cvs
 */

@Component
public class CountryCodeLoader {

    private final CountryCodeRepository countryCodeRepository;

    @Autowired
    public CountryCodeLoader(CountryCodeRepository countryCodeRepository) {
        this.countryCodeRepository = countryCodeRepository;
    }

    @PostConstruct
    public void loadCountryCodes() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/main/resources/country_codes.csv"));

        for (String line : lines) {
            String[] parts = line.split(",");
            String country = parts[0];
            String code = parts[1];
            List<String> additionalCodes = Arrays.asList(parts).subList(2, parts.length);

            CountryCode countryCode = new CountryCode();
            countryCode.setCountry(country);
            countryCode.setCode(code);
            countryCode.setAdditionalCodes(additionalCodes);

            countryCodeRepository.save(countryCode);
        }
    }
}