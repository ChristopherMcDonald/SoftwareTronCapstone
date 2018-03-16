import socket, time;

# global variables
HOST = "localhost";
PORT = 8013;

socketIn = socket.socket();             # handles incoming requests
socketIn.bind((HOST, PORT));            # binds to a host and port
socketIn.listen(5);                     # enables server to accept incoming

while True:                             # loop forever
    conn, addr = socketIn.accept();     # loop will freeze on this command, runs when SmartServe
    print("Got connection from", addr); # sends a request
    msg = conn.recv(1024);              # parses msg... could be "TEST", "DETECT"
    
    # DEBUGGING
    print(msg);
    
    # DEBUGGING
    time.sleep(2);
    
    # socket for outgoing messages
    socketOut = socket.socket(socket.AF_INET, socket.SOCK_STREAM);
    socketOut.connect((HOST, PORT + 1));
    
    # DEBUGGING
    time.sleep(2);
 
    if("TEST" in msg.decode("UTF8")):
        socketOut.send(b'ALLGOOD\n');
    else: 
        socketOut.send(b'RETURNED\n');