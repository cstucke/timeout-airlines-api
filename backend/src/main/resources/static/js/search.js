document.getElementById("searchForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const dep = document.getElementById("departureCity").value;
    const arr = document.getElementById("arrivalCity").value;
    const date = document.getElementById("departureDate").value;

    fetch(`/flights/search?departureCity=${dep}&arrivalCity=${arr}&date=${date}`)
        .then(res => res.json())
        .then(data => displayFlights(data));
});

function displayFlights(flights) {
    const tbody = document.querySelector("#resultsTable tbody");
    tbody.innerHTML = "";

    flights.forEach(f => {
        if (f.numberOfSeats > 0) {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${f.flightNumber}</td>
                <td>${f.departureCity}</td>
                <td>${f.arrivalCity}</td>
                <td>${f.departureTime}</td>
                <td>${f.numberOfSeats}</td>
                <td>
                    <a href="booking.html?flight=${f.flightNumber}">
                        Book
                    </a>
                </td>
            `;
            tbody.appendChild(row);
        }
    });
}
