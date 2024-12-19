<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <link rel="stylesheet" href="profile.css">
</head>
<body>
    <div class="profile-container">
        <h2>My Profile</h2>
        <form action="updateProfile" method="POST">
            <div class="input-group">
                <label for="Name">Name:</label>
                <input type="text" id="Name" name="Name" value="${Name}" required>
            </div>
            <div class="input-group">
                <label for="mobileNumber">Mobile Number:</label>
                <input type="text" id="mobileNumber" name="mobileNumber" value="${mobileNumber}" required>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${email}" required>
            </div>
            <div class="input-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="male" ${gender == 'male' ? 'selected' : ''}>Male</option>
                    <option value="female" ${gender == 'female' ? 'selected' : ''}>Female</option>
                </select>
            </div>
            <div class="input-group">
                <label for="age">Age:</label>
                <input type="number" id="age" name="age" value="${age}" required>
            </div>
            <div class="input-group">
                <label for="fieldOfInterest">Field of Interest:</label>
                <input type="text" id="fieldOfInterest" name="fieldOfInterest" value="${fieldOfInterest}" required>
            </div>
            <button type="submit">Save Changes</button>
            
        </form>
    </div>
</body>
</html>
