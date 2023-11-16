package kioskProject;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class CoffeeMachine extends JFrame {
	JPanel CoffeeMenu, CoffeeOrder, Payment;
	JTextArea totalMenu;
	JLabel EX;
	
	int count = 0;
	
	public CoffeeMachine() {
		setTitle("커피 머신");
		setSize(420,500);
		setResizable(false);
		
		JFrame frame = new JFrame();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		JLabel main = new JLabel("커피를 주문하세요");
		add(main, BorderLayout.CENTER);

		
		
		//커피 메뉴 패널과 버튼
		CoffeeMenu = new JPanel(); add(CoffeeMenu);
		CoffeeMenu.setLayout(new GridLayout(3,2,10,5));
		
		String[] coffeetext = {"아메리카노", "카페라떼", "카페모카", "카푸치노", "돌체라떼", "콜드브루"};
		JButton[] coffee = new JButton[coffeetext.length];
		
		String[] coffeePriceText = {"2000원", "2500원", "3000원", "3000원", "4000원", "4500원"};
		JLabel[] coffeePrice = new JLabel[coffeePriceText.length];
		

		for (int i=0; i < coffeetext.length; i++) {
			coffee[i] = new JButton(coffeetext[i]);
			CoffeeMenu.add(coffee[i]);
			coffeePrice[i] = new JLabel(coffeePriceText[i]);
			CoffeeMenu.add(coffeePrice[i]);
			coffee[i].addActionListener(new MyActionListener());
		}
		
		
	    //입력받은 주문 내역
		totalMenu = new JTextArea("메뉴\t"+"가격\t"+"누적 수량\t"+"누적 가격"+"\t\n", 7, 35);
		add(totalMenu, BorderLayout.CENTER);
		totalMenu.setEditable(false);
		totalMenu.setBackground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(totalMenu);
		add(scrollPane);
		totalMenu.setCaretPosition(totalMenu.getDocument().getLength());

		
		//주문 내역 확인
		CoffeeOrder= new JPanel(); add(CoffeeOrder);

		JButton OK = new JButton("확인");
		JButton CANCEL = new JButton("주문 취소");
		
		CoffeeOrder.setLayout(new FlowLayout());
		CoffeeOrder.add(OK);
		CoffeeOrder.add(CANCEL);
		
		//주문 취소 버튼 누르면 초기화
		CANCEL.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				totalMenu.setText("\t"+"메뉴\t"+"가격\t"+"누적 수량\t"+"\t\n");
				count = 0;
				EX.setText("총 액: " + count + "");
				
				//주문 취소 버튼 누르면 커피버튼 활성화
				for (int i=0; i < coffee.length; i++) {
					coffee[i].setEnabled(true);
				}
			}
		});
		

		OK.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				
			//확인 버튼 누르면 더 이상 주문할 수 없도록 버튼 비활성화
			for (int i=0; i < coffee.length; i++) {
				coffee[i].setEnabled(false);
			}
		
				
			Payment = new JPanel(); add(Payment);
			Payment.setLayout(new GridLayout(3,1));
			
			JLabel PaymentMent1 = new JLabel("결제 수단을 선택해 주세요.");
			JLabel PaymentMent2 = new JLabel("※현금 결제는 불가능합니다.");
			 
			Payment.add(PaymentMent1);
			Payment.add(PaymentMent2);
			
			String[] PaymentText = {"카드", "모바일 페이", "기프티콘"};
			JButton[] PaymentBtn = new JButton[PaymentText.length];
			
			for (int i=0; i < PaymentText.length; i++) {
				PaymentBtn[i] = new JButton(PaymentText[i]);
				Payment.add(PaymentBtn[i]);
				PaymentBtn[i].addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
						
						//팝업 창 클릭 후 내용 초기화
						for (int i=0; i < coffee.length; i++) {
							coffee[i].setEnabled(true);
						}
						totalMenu.setText("메뉴\t"+"가격\t"+"누적 수량\t"+"누적 가격"+"\t\n");
						count = 0;
						EX.setText("총 액: " + count + "");
						Payment.setVisible(false);

					}
				});
			       
			}
				
			setVisible(true);

			}
		});
		
		EX = new JLabel("총 액: " + count + ""); CoffeeOrder.add(EX);

		
		//화면 보이기
		setVisible(true);
		
	}
	
	class MyActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();

			
			if (b.getText().equals("아메리카노")){
				count += 2000;
				EX.setText("총 액: " + count + "");
				totalMenu.append("아메리카노\t"+"2000원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");
			}
			else if (b.getText().equals("카페라떼")){
				count += 2500;
				EX.setText("총 액: " + count + "");
				totalMenu.append("카페라떼\t"+"2000원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");
			}
			else if (b.getText().equals("카페모카")){
				count += 3000;
				EX.setText("총 액: " + count + "");
				totalMenu.append("카페모카\t"+"2000원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");
			}
			else if (b.getText().equals("카푸치노")){
				count += 3000;
				EX.setText("총 액: " + count + "");
				totalMenu.append("카푸치노\t"+"3000원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");
			}
			else if (b.getText().equals("돌체라떼")){
				count += 4000;
				EX.setText("총 액: " + count + "");
				totalMenu.append("돌체라떼\t"+"4000원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");
			}			
			else if (b.getText().equals("콜드브루")){
				count += 4500;
				EX.setText("총 액: " + count + "");
				totalMenu.append("콜드브루\t"+"4500원\t"+(totalMenu.getLineCount()-1) +"\t"+""+count+"\n");		
			}

		}
	}
	
	public static void main(String[] args) {
		new CoffeeMachine();
		
	}
}