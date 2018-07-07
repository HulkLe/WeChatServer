package sever;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class SeverUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JTextArea log;
	JTable list;
	JScrollPane jsclog;
    JScrollPane jsclist;
    JButton exit;
   
	public SeverUI()
	{
		this.setDefaultCloseOperation(0);
    	this.setTitle("服务器端");
    	this.setSize(900,700);
    	this.setResizable(false);
    	setLayout(null);
		
    	//关闭按钮
    	exit=new JButton("关闭");
    	exit.setBounds(790,550,60,40);
    	exit.setFont(new Font("楷体",Font.BOLD,11));
    	this.add(exit);

    	exit.addActionListener(new ActionListener() {//添加退出事件
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
    	});
    	//日志
		log=new JTextArea();
		log.setLineWrap(true);
    	log.setEditable(false);
    	log.setFont(new Font("宋体",Font.BOLD,16));
		
    	jsclog=new JScrollPane(log);
    	jsclog.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	jsclog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	jsclog.setBounds(20, 20, 400, 600);
    	this.add(jsclog);
		
    	//表格
    	String[] columnTitle= {"在线者","IP","port"};
    	String[][] rowdata=null;
    	list=new JTable(new DefaultTableModel(rowdata,columnTitle) 
    	{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row,int column)
    		{
    			return false;
    		}
    	});
    	list.setFont(new Font("楷体",0,15));
    	
    	jsclist=new JScrollPane(list);
    	jsclist.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	jsclist.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	jsclist.setBounds(450, 20, 400, 500);
    	this.add(jsclist);
 
		this.setVisible(true);
	}
}
