const params = new URLSearchParams(window.location.search);
document.getElementById("flightNumber").value = params.get("flight");

document.getElementById("bookingForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const data = {
        flightNumber: document.getElementById("flightNumber").value,
        passportNumber: document.getElementById("passport").value,
        seatType: document.getElementById("seatType").value
    };

    fetch("/books", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(() => {
            document.getElementById("message").innerText =
                "Booking successful!";
        });
});
