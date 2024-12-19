// Initialize WebSocket connection
function initChat() {
  const chatBox = document.getElementById('chatBox');
  const messageInput = document.getElementById('messageInput');
  const sendButton = document.getElementById('sendButton');

  // WebSocket URL (Replace localhost with the correct server address if needed)
  const socket = new WebSocket('ws://localhost:8080/chat');

  // Function to append messages to the chat box
  function appendMessage(sender, message) {
    const messageDiv = document.createElement('div');
    messageDiv.textContent = `${sender}: ${message}`;
    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight; // Auto-scroll to the latest message
  }

  // WebSocket events
  socket.onopen = () => {
    console.log('WebSocket connection established.');
    appendMessage('System', 'You are connected.');
  };

  socket.onmessage = (event) => {
    const data = JSON.parse(event.data);
    appendMessage(data.senderId, data.messageText);
  };

  socket.onerror = (error) => {
    console.error('WebSocket Error:', error);
    appendMessage('System', 'An error occurred.');
  };

  socket.onclose = () => {
    console.log('WebSocket connection closed.');
    appendMessage('System', 'Connection closed.');
  };

  // Send message on button click
  sendButton.addEventListener('click', () => {
    const messageText = messageInput.value.trim();
    if (messageText) {
      const message = {
        senderId: 'mentor1', // Replace with dynamic sender ID
        receiverId: 'student1', // Replace with dynamic receiver ID
        messageText: messageText,
      };
      socket.send(JSON.stringify(message)); // Send message via WebSocket
      appendMessage('You', messageText); // Display message locally
      messageInput.value = ''; // Clear the input field
    }
  });

  // Send message on Enter key press
  messageInput.addEventListener('keypress', (event) => {
    if (event.key === 'Enter') {
      sendButton.click();
    }
  });
}

// Initialize chat
initChat();
