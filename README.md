# Phone Country Detector

Приложение для определения страны по номеру телефона.

## Требования
- Java 17 или выше
- Gradle 7.x или выше
- PostgreSQL

## Установка и запуск

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/Novak198/phone-country-detector.git
   cd phone-country-detector
   
2. Откройте файл "application.properties"
   Затем в поле - spring.datasource.password= введите свой пароль от Postgres

3. **Соберите проект:**
   ./gradlew clean build

4. **Запустите приложение:**
   java -jar build/libs/phone-country-detector-1.0.0.jar

5. **Откройте браузер**
   Перейдите по адресу - http://localhost:8088 , введите номер телефона и нажмите клавишу - "Узнать страну".