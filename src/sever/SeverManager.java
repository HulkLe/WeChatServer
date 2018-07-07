package sever;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class SeverManager {
	//单例模式
	public static SeverManager manager=new SeverManager();
	//储存所有socket
	HashMap<String,Socket> map=new HashMap<String, Socket>();
	
	SeverUI ui;
	public void SetSeverUI(SeverUI ui)
	{
		this.ui=ui;
	}
	//转发消息
	public void pulish(String mag) throws UnsupportedEncodingException, IOException {
		
		String goal=mag.substring(0,mag.indexOf(":"));
		String sender=mag.substring(mag.indexOf(":")+1,mag.indexOf("/"));
		String content=mag.substring(mag.indexOf("/")+1);
		if(goal.equals("群聊天室"))//如果是群聊
		{     
			for(String key: map.keySet())
			{
		       map.get(key).getOutputStream().write(mag.getBytes());
			}
		}
		else 
		{          
			map.get(goal).getOutputStream().write(mag.getBytes());
			map.get(sender).getOutputStream().write(mag.getBytes());
		}	
		 ui.log.append(sender+"->"+goal+":\n"+content+"\n\n");
	}
	
	public void updatelist() throws IOException 
	{	
		StringBuffer buf=new StringBuffer();
		buf.append("群聊天室");
		for(String key:map.keySet())
		{
			buf.append(",");
		   	buf.append(key);
		}
		for(String key:map.keySet())
		{
		 map.get(key).getOutputStream().write(("updatelist:"+buf+"/"+"无消息").getBytes());  
		}	
	}
	
	public void addClient(String name,Socket socket) throws IOException
	{
		map.put(name, socket);
		updatelist();
		
		DefaultTableModel tbm = (DefaultTableModel) ui.list.getModel();
		String []tmp=new String[3];
		tmp[0]=name;
		tmp[1]=socket.getInetAddress().getHostAddress();
		tmp[2]=String.valueOf(socket.getPort());
		tbm.addRow(tmp);
		DefaultTableCellRenderer tbr = new DefaultTableCellRenderer();//提取在线列表的渲染模型
        tbr.setHorizontalAlignment(JLabel.CENTER);  //表格数据居中显示
        ui.list.setDefaultRenderer(Object.class, tbr);
	}
	
	//所有客户端删除user
	public void exit(String user) throws IOException
	{
		map.remove(user);
		updatelist();
	}
	
}
