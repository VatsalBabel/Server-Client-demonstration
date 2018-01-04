import socket
import threading
import sys

def sending():
	try:
		while(True):
			smessage = input()
			client.sendall(smessage.encode('utf-16'))
		else:
			print("Connection Lost.")
			sys.exit()
	except:
		print("Connection Lost.")
	finally:
		client.close()

def listening():
	try:
		while(True):
			lmessage = client.recv(1024)
			print(oname.decode('utf-16')+" >> "+lmessage.decode('utf-16'))
		else:
			print("Connection Lost.")
	except:
		print("Connection Lost.")
	finally:
		client.close()


name = input("Enter your name-\n")
print()

client = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
host = socket.gethostname()
port = 5000
client.connect((host,port))
print("Connected to server.")
client.sendall(name.encode('utf-16'))
oname = client.recv(1024)

thread1 = threading.Thread( target = sending )
thread2 = threading.Thread( target = listening )
thread1.start()
thread2.start()
