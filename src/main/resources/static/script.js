function detectCountry() {
    const phoneNumber = document.getElementById("phoneNumber").value;

    if (!phoneNumber) {
        document.getElementById("result").innerText = "Пожалуйста введите номер телефона.";
        return;
    }

    fetch(`http://localhost:8088/api/detect-country?phoneNumber=${phoneNumber}`)
        .then(response => response.json())
        .then(data => {
            if (data.country) {
                document.getElementById("result").innerText = "Страна: " + data.country;
            } else {
                document.getElementById("result").innerText = "Ошибка: " + data.error;
            }
        })
        .catch(error => {
            document.getElementById("result").innerText = "There was an error with the request.";
            console.error('Error:', error);
        });
}