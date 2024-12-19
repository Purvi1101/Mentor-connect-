<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor Dashboard - Schedule Sessions</title>
    <style>
        /* General Styles */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    color: #333;
    line-height: 1.6;
}

h1, h2 {
    color: #444;
}

/* Header */
h1 {
    text-align: center;
    margin-top: 20px;
}

/* Form Styles */
form {
    max-width: 600px;
    margin: 20px auto;
    background: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
}

form input[type="text"],
form input[type="date"],
form input[type="time"],
form input[type="number"],
form textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
}

form textarea {
    resize: vertical;
}

form button {
    display: inline-block;
    background: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

form button:hover {
    background: #0056b3;
}

/* Table Styles */
table {
    width: 90%;
    margin: 20px auto;
    border-collapse: collapse;
    background: #fff;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table th,
table td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

table th {
    background: #007bff;
    color: #fff;
    font-weight: bold;
    text-transform: uppercase;
}

table tr:hover {
    background: #f1f1f1;
}

/* Container for Sessions */
#sessionTable {
    text-align: center;
}

/* Buttons */
button {
    padding: 10px 20px;
    margin: 5px;
    font-size: 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: all 0.3s ease;
}

button:hover {
    background-color: #555;
    color: white;
}

/* Responsive Design */
@media (max-width: 768px) {
    form {
        padding: 10px;
    }

    table {
        width: 100%;
    }

    table th,
    table td {
        padding: 8px;
    }
}

    </style>
</head>
<body>
    <h1>Mentor Dashboard - Schedule Sessions</h1>

    <h2>Schedule a New Session</h2>
    <form action="ScheduleSessionServlet" method="POST">
        <label for="sessionName">Session Name:</label><br>
        <input type="text" id="sessionName" name="sessionName" required><br><br>

        <label for="date">Date:</label><br>
        <input type="date" id="date" name="date" required><br><br>

        <label for="time">Time:</label><br>
        <input type="time" id="time" name="time" required><br><br>

        <label for="duration">Duration (in minutes):</label><br>
        <input type="number" id="duration" name="duration" required><br><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50"></textarea><br><br>

        <button type="submit">Schedule Session</button>
    </form>

    <h2>Your Scheduled Sessions</h2>
    <table>
        <tr>
            <th>Session Name</th>
            <th>Date</th>
            <th>Time</th>
            <th>Duration</th>
            <th>Description</th>
        </tr>
        <tbody id="sessionTable">
            <!-- Scheduled sessions will be dynamically loaded -->
        </tbody>
    </table>

    <script>
        // Fetch and load sessions when the page loads
        window.onload = function() {
            fetch('FetchScheduledSessionsServlet')
                .then(response => response.json())
                .then(sessions => {
                    const sessionTable = document.getElementById('sessionTable');
                    sessionTable.innerHTML = sessions.map(session => `
                        <tr>
                            <td>${session.sessionName}</td>
                            <td>${session.date}</td>
                            <td>${session.time}</td>
                            <td>${session.duration} minutes</td>
                            <td>${session.description}</td>
                        </tr>
                    `).join('');
                })
                .catch(err => alert("Error loading sessions: " + err));
        };
    </script>
</body>
</html>
