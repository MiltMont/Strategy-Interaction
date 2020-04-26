import socket
#1.- initialize values
#2.- create method to connect
#3.- create method to send a string
#4.- in the same method append '\n' to the end of the line

class Client:

	def __init__(self):
		self.HOST = 'localhost'
		self.PORT = 8080
		self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

	def connect(self):
		self.__init__()
		self.sock.connect((self.HOST, self.PORT))

	def send(self, content):
		self.sock.sendall('{}\n'.format(content).encode())
