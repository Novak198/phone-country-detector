package test.phone_country_detector.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.phone_country_detector.entity.CountryCode;

import java.util.List;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {

    @Query("""
            SELECT DISTINCT cc, LENGTH(cc.code) FROM CountryCode cc 
            LEFT JOIN cc.additionalCodes ac 
            WHERE :phoneNumber LIKE CONCAT(cc.code, '%') 
               OR :phoneNumber LIKE CONCAT(ac, '%') 
            ORDER BY LENGTH(cc.code) DESC
            """)
    List<CountryCode> findAllMatchingCodes(@Param("phoneNumber") String phoneNumber);
}