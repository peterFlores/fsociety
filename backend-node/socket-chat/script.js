const socket = io('http://localhost:3000');
const messageContainer = document.getElementById('message-container');
const messageForm = document.getElementById('send-container');
const messageInput = document.getElementById('message-input');

const name = prompt('Cómo te llamas?')
const room = prompt('id del room')

socket.emit('new-user', name);
socket.emit('join-room', room);

socket.on('chat-message', data => {
    appendMessage(`${data.name}: ${data.message}`)
})


messageForm.addEventListener('submit', e => {
    e.preventDefault() //evita que se actualice la pagina completa
    const message = messageInput.value
    socket.emit('send-chat-message', {room, message})
    messageInput.value = ''
    appendMyMessage(`Tú: ${message}`)
})

function appendMessage(message) {   
    const messageElement = document.createElement('div')
    messageElement.className = 'alert alert-secondary'
    messageElement.innerText = message
    messageContainer.append(messageElement)
    messageContainer.scrollTop = messageContainer.scrollHeight;
}

function appendMyMessage(message) {   
    const messageElement2 = document.createElement('div')
    messageElement2.className = 'alert alert-success';
    messageElement2.innerText = message;
    messageContainer.append(messageElement2)
    messageContainer.scrollTop = messageContainer.scrollHeight;
}