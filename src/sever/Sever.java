package sever;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Sever extends Thread{
	public boolean op=true;
	public void run() {
		
		try {
			SeverUI ui=new SeverUI();
			SeverManager.manager.SetSeverUI(ui);
			ServerSocket serversocket=new ServerSocket(10000);
			while(op)
            {
				Socket socket=serversocket.accept();
				String ip=socket.getInetAddress().getHostAddress();
				int port=socket.getPort();
				
				System.out.println("有客户端"+ip+":"+String.valueOf(port)+"连接到服务器\n");

				ServerChat serverchat=new ServerChat(socket);
				serverchat.start();
			}
			serversocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
