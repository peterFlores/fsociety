const io = require('socket.io')(3000)

const users = {}
const rooms = {}

io.on('connection', socket => {

    socket.on('new-user', name => {
        users[socket.id] = name
        socket.broadcast.emit('user-connected', name)
    })
    

    socket.on('join-room', room => {
        socket.join(room);
    });
    
    socket.on('disconnect', () => {

    });

    socket.on('send-chat-message', ({room, message}) => {
        socket.to(room).emit('chat-message',  {message: message, name: users[socket.id]});
    });
})
