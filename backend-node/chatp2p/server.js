// configuracion de los packetes node y template html

const http = require('http');
const express = require ('express');
const socketio = require('socket.io')
const app = express();

const server = http.createServer(app);
const io = socketio(server);

const PORT = 5000 || process.env.PORT;

//Iniciar a segun la solicitud.

io.on('connection', socket =>{
    socket.emit('message', 'Bienvenido al Chat');
    // Notificacion de conexion.
    socket.broadcast.emit('message', 'Usuario conectado.');
    // Notificacion de desconexion.
    socket.on('disconnect', () => {
        io.emit('message','Usuario abandono el chat.');
    });

    //Escuchando los mensajes entrantes.
    socket.on('chatMessage', (msg) => {
        io.emit('messege',msg);
        console.log(msg);    
    });

});



server.listen(PORT, () => console.log(`Server Running on PORT ${PORT}`));




