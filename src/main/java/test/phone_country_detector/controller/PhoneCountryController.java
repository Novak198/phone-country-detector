package test.phone_country_detector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import test.phone_country_detector.service.PhoneCountryService;

import java.util.Map;

/**
 * Контроллер который возвращает index.html по пути - http://localhost:8088/
 */

@Controller
@RequestMapping("/api")
public class PhoneCountryController {

    private final PhoneCountryService phoneCountryService;

    @Autowired
    public PhoneCountryController(PhoneCountryService phoneCountryService) {
        this.phoneCountryService = phoneCountryService;
    }

    @GetMapping()
    public String showForm() {
        return "index";
    }

    @GetMapping(value = "/detect-country", params = "phoneNumber")
    public ResponseEntity<Map<String, String>> detectCountry(@RequestParam String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Требуется номер телефона"));
        }

        String country = phoneCountryService.detectCountry(phoneNumber);

        if (!country.equals("Unknown country")) {
            return ResponseEntity.ok(Map.of("country", country));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Страна не найдена"));
        }
    }
}