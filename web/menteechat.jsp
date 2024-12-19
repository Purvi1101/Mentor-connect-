<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentee Chat</title>
</head>
<body>
    <h1>Mentee Chat</h1>
    <div id="chatContainer">
        <!-- List of Mentors -->
        <div id="chatList" style="border: 1px solid #ccc; margin-bottom: 10px; padding: 10px;">
            <h3>Mentors</h3>
        </div>

        <!-- Chat messages -->
        <div id="chatBox" style="height:300px; overflow-y:scroll; border: 1px solid #ccc; margin-bottom: 10px; padding: 10px;">
            <h3>Chat</h3>
        </div>

        <!-- Input to send messages -->
        <input type="text" id="messageInput" placeholder="Type your message here" style="width: 80%;">
        <button onclick="sendMessage()">Send</button>
    </div>

    <script>
        const senderId = "<%= session.getAttribute("userId") %>"; // Logged-in mentee's ID
        let receiverId = ""; // Mentor's ID, set dynamically

        function loadMentors() {
            fetch('/getChatUsers') // Backend servlet to fetch mentors
                .then(response => response.json())
                .then(users => {
                    const chatList = document.getElementById('chatList');
                    chatList.innerHTML = users.map(user =>
                        `<div onclick="setReceiver('${user.id}')">${user.name}</div>`
                    ).join('');
                });
        }

        function setReceiver(id) {
            receiverId = id;
            fetchMessages();
        }

        function fetchMessages() {
            if (!receiverId) return alert("Please select a mentor to chat with!");
            fetch(`/fetchMessages?senderId=${senderId}&receiverId=${receiverId}`)
                .then(response => response.json())
                .then(messages => {
                    const chatBox = document.getElementById('chatBox');
                    chatBox.innerHTML = messages.map(msg =>
                        `<div><strong>${msg.senderId}</strong>: ${msg.messageText} <small>${new Date(msg.timestamp).toLocaleString()}</small></div>`
                    ).join('');
                });
        }

        function sendMessage() {
            if (!receiverId) return alert("Please select a mentor to chat with!");
            const messageText = document.getElementById('messageInput').value;
            if (messageText.trim() === "") return;

            fetch('/sendMessage', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `senderId=${senderId}&receiverId=${receiverId}&messageText=${messageText}`
            }).then(() => {
                document.getElementById('messageInput').value = '';
                fetchMessages();
            });
        }

        window.onload = loadMentors;
    </script>
</body>
</html>
