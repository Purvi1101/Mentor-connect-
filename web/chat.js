function initChat(role) {
  var chatBox = document.getElementById('chatBox');
  var messageInput = document.getElementById('messageInput');
  var sendButton = document.getElementById('sendButton');

  // Fetch messages from server periodically
  setInterval(() => {
    fetch(`/ChatServlet?role=${role}`)
      .then(response => response.json())
      .then(messages => {
        chatBox.innerHTML = '';
        messages.forEach(msg => {
          var messageElement = document.createElement('div');
          messageElement.textContent = `[${msg.senderRole}] ${msg.content}`;
          chatBox.appendChild(messageElement);
        });
      });
  }, 1000);

  // Send message to the server
  sendButton.addEventListener('click', () => {
    const message = {
      senderRole: role,
      senderId: Math.random().toString(36).substr(2, 9), // Random ID
      content: messageInput.value
    };

    fetch('/ChatServlet', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(message)
    });

    messageInput.value = '';
  });
}
