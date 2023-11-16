import socket

serverName = '127.0.0.1'
serverPort = 12000

def getFileFromServer(fileName):
    data_transferred = 0

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((serverName, serverPort))
        sock.sendall(fileName.encode())

        data = sock.recv(1024)

        with open(fileName, 'wb') as f:
            try:
                while data:
                    f.write(data)
                    data_transferred += len(data)
                    data = sock.recv(1024)
            except Exception as e:
                print(e)

fileName = input("파일 이름을 입력하시오: ")
getFileFromServer(fileName)