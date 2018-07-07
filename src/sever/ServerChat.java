package sever;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
public class ServerChat extends Thread{
    
    Socket socket;	
	byte[] buf = new byte[1024];
	
	public ServerChat(Socket socket) {
		this.socket=socket;
	}
	
	public void run() {       
		
		try {
			InputStream in=socket.getInputStream();
           
		    while(true) {
		    	
		    	int len=in.read(buf);
		    	String line=new String(buf,0,len);
		    	String head=line.substring(0,line.indexOf(":"));//:前面表示类型，/前面表示发送者
		    	String mid=line.substring(line.indexOf(":")+1, line.indexOf("/"));
		      	if(head.equals("Exit")) 
		      	 {
		    		SeverManager.manager.exit(mid);
		    		break;
		    	 }
		      	else if(head.equals("updatelist")) 
		      	 {
		      		SeverManager.manager.addClient(mid, socket);
		      		//SeverManager.manager.map.put(mid, socket);
		      		//SeverManager.manager.updatelist();
		      	 }
		         else 
		         {
		    		SeverManager.manager.pulish(line);//socket收到后用Manager决定怎么转发
		         }
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
