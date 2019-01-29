package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class PlayView extends JFrame {
	
	JPanel LowerPanel, UpperPanel, PlayPanel;
//	
	JTextField input_text = new JTextField (40);
	JLabel la_life,  la_level, la_score, la_best, la_time, la_notice;
//	
	BorderLayout bd = new BorderLayout();
//	
	public JButton btn_back;
	JButton btn_over; // 일단 생성만하고 아래엔 주석처리해놓음
//	
	static int sec = 0; // 스테틱으로 해놔야됨
	
	boolean isitthefirst = true;
	boolean onlyfortimer = true;
	
	Drop d = new Drop();
	Drop d1 = new Drop();
	Drop d2 = new Drop();
	Drop d3 = new Drop();
	
	public PlayView() {
				
		LowerPanel = new JPanel ();
		UpperPanel = new JPanel ();
		PlayPanel = new JPanel ();
		
		la_life = new JLabel ("Life : ★ ★ ★");
		 la_level = new JLabel ("Level : ★ ★ ★");
		 la_score = new JLabel ("Score : ★ ★ ★");
		 la_best = new JLabel ("Best Score : ★ ★ ★");
		 la_time = new JLabel ("Time Left");
		
		 btn_back = new JButton(" << ");
	//UpperPanel============================
	UpperPanel.setLayout(new FlowLayout(1,20,10));

	UpperPanel.add(btn_back);
	UpperPanel.add( la_level);
	UpperPanel.add(la_score);
	UpperPanel.add(la_best);
	UpperPanel.add(la_time);
	
	UpperPanel.setBackground(Color.BLACK);
	
	 la_level.setForeground(Color.white);
	 la_score.setForeground(Color.white);
	 la_best.setForeground(Color.white);
	 la_time.setForeground(Color.white);
	
	// PlayPanel=============================
	
	PlayPanel.setLayout(null);
	   d.setBounds(0, 0, 100, 30);
	  PlayPanel.add(d);
	
	  d1.setBounds(0, 0, 100, 30); // 두번째 쓰레드 생성만해놓음
	  d1.setText("두번째다잉");
	  PlayPanel.add(d1);
	  
	  d2.setBounds(0, 0, 100, 30); // 세번째 쓰레드 생성만해놓음
	  d2.setText("세번째다잉");
	  PlayPanel.add(d2);
	  
	  d3.setBounds(0, 0, 100, 30); // 네번째 쓰레드 생성만해놓음
	  d3.setText("네번째다잉");
	  PlayPanel.add(d3);
	  
	  new Thread(d).start(); // 첫번째 쓰레드 시작!

		
	
	
	  la_notice = new JLabel("시작하려면 아무 글자나 입력해주세요");
	  la_notice.setFont(new Font("굴림", Font.BOLD, 20));
	  la_notice.setHorizontalAlignment(SwingConstants.CENTER);
	  la_notice.setBounds(105, 247, 383, 60);
	PlayPanel.add(la_notice);
	
//	overButton = new JButton("Next Round");
	//overButton.setBounds(228, 277, 137, 44);
	//PlayPanel.add(overButton);
	//overButton.setVisible(false);
	
	// LowerPanel============================
	
	LowerPanel.add(input_text);
	LowerPanel.add(la_life);
	
	// Timer =================================
	
	Timer timer= new Timer(1000,new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			sec++;
			// 시간 이렇게 정한 이유는 그래야 간격이 좀 맞는 것 같아서, 수정해도 ㄱㅊ
			  if (PlayView.sec==30 )	  new Thread(d1).start();
			  if (PlayView.sec==47 )	  new Thread(d2).start();
			  if (PlayView.sec==60 )	  new Thread(d3).start();
			  la_time.setText("" + sec);

		if(sec<10) {la_time.setForeground(Color.red);};	
		}
	});
	
	Timer round_over= new Timer(10000,new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		//overButton.setVisible(true);

		}
	});
	// Textfield ==============================

	input_text.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.start();
			round_over.start();
			la_notice.setVisible(false);
			input_text.setText("");
		}
	});
	//프레임 성격 ==============================
	
	getContentPane().add(UpperPanel, bd.NORTH);
	getContentPane().add(LowerPanel, bd.SOUTH);
	getContentPane().add(PlayPanel);
	
	setSize(600,700);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setVisible(false);
		
	}
}
