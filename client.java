//Creating a Client socket in java

import java.io.*;
import java.net.*;
import java.util.Scanner;

class listening extends Thread{
	Socket s;
	listening(Socket s){
		this.s = s;
	}
	public void run(){
		try{  
			while(true){
				DataInputStream dis=new DataInputStream(s.getInputStream());  
				String str=(String)dis.readUTF();  
				System.out.println(str);
			}
		}catch(Exception e){
			System.out.println("Disconnected");
			System.exit(0);
		}
	}
}

class sending extends Thread{
	Socket s;
	Scanner sc = new Scanner(System.in);
	sending(Socket s){
		this.s = s;
	}
	public void run(){
		try{       
			while(true){
				DataOutputStream dout=new DataOutputStream(s.getOutputStream());
				String message = sc.nextLine();
				dout.writeUTF(message);  
				dout.flush();  
			}
		}catch(Exception e){
			System.out.println("Disconnected");
			System.exit(0);
		}finally{
			sc.close();
			try{
				s.close();
			}catch(Exception e){
			}
		}
	}
}
class client{
	public static void main(String args[]) throws IOException{
		String host = "127.0.0.1";
		int port = 6666;
		Socket s=new Socket(host,port);
		System.out.println("Connected");
		listening listen = new listening(s);
		listen.start();
		sending send = new sending(s);
		send.start();		
	}
}