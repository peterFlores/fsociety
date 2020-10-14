const chatForm = document.getElementById('chat-form');

const socket = io();


socket.on('message', message => {
    console.log(message);
    outputMessage(message);
});

// Envio de Mensajes.

chatForm.addEventListener('submit', (e) =>{
    e.preventDefault();

    const msg = e.target.elements.msg.value;

    //emitiendo el mensaje al server
    socket.emit('chatMessage', msg);


});

