from socket import *
import os

serverPort = 12000
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind(('', serverPort))
serverSocket.listen(1)


fileList = os.listdir("C:\\temp1")
fileListString = ', '.join(fileList)
print("전송 가능한 파일: ", fileListString)
fileList = os.chdir("C:\\temp1")

dataTransferred = 0

while True:
    connectionSocket, addr = serverSocket.accept()

    fileName = connectionSocket.recv(1024)
    print(fileName.decode())

    with open (fileName, 'rb') as f:
        connectionSocket.sendfile(f,0)

    print("전송했습니다.")
    connectionSocket.close()