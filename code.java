import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class besenica2 extends JFrame {

	private JPanel contentPane;
	private static final String[] WORDS = {"дърво", "банан", "портокал", "манго", "куче","къща","прозорец","ветрило","врата","чадър","щора","автомобил","климатик"};
    private static final int MAX_TRIES = 8;

    private JLabel duma;
    private JLabel opiti;
    private JTextField textField;
    private JButton btnguess;
    private String word;
    private char[] guessedWord;
    private int tries;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					besenica2 frame = new besenica2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public besenica2() {
		    duma = new JLabel();
		    duma.setFont(new Font("Tahoma", Font.PLAIN, 20));
			duma.setHorizontalAlignment(SwingConstants.CENTER);
			duma.setBounds(90, 100, 183, 41);
			duma.setVisible(false);
			
	        opiti = new JLabel();  
	        opiti.setFont(new Font("Tahoma", Font.PLAIN, 20));
			opiti.setHorizontalAlignment(SwingConstants.CENTER);
			opiti.setBounds(139, 148, 183, 41);
			opiti.setVisible(false);
			
	        textField = new JTextField();
	       
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 478, 509);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(219, 162, 219));
		panel.setBounds(0, 0, 465, 470);
		contentPane.add(panel);
		panel.setLayout(null);
		
		    panel.add(duma);
	        panel.add(opiti);
	        
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(139, 200, 183, 41);
		panel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("БЕСЕНИЦА");
		lblNewLabel.setForeground(new Color(204, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		lblNewLabel.setBounds(0, 42, 465, 68);
		panel.add(lblNewLabel);
		
		JButton exit = new JButton("ИЗХОД");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exit.setBackground(new Color(204, 153, 255));
		exit.setForeground(new Color(204, 255, 255));
		exit.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		exit.setBounds(262, 352, 180, 41);
		panel.add(exit);
		exit.setVisible(false);
		
		JButton btnguess = new JButton("ПОЗНАЙ");
		btnguess.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				 processGuess();
			}
		});
		
		exit.setVisible(false);
		btnguess.setVisible(false);
		btnguess.setForeground(new Color(204, 255, 255));
		btnguess.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnguess.setBackground(new Color(204, 153, 255));
		btnguess.setBounds(20, 352, 180, 41);
		panel.add(btnguess);
		
		JButton start = new JButton("СТАРТ");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start.setVisible(false);
				opiti.setVisible(true);
				lblNewLabel.setVisible(false);
				textField.setVisible(true);
				//man.setVisible(true);
				exit.setVisible(true);
				btnguess.setVisible(true);
				duma.setVisible(true);
	            setWord(); 
	            updateWordLabel(); 
	            opitileft();

			}
		});
		start.setBackground(new Color(204, 153, 255));
		start.setForeground(new Color(204, 255, 255));
		start.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		start.setBounds(159, 252, 140, 49);
		panel.add(start);
	}
	
	private void setWord() {
	    word = WORDS[(int) (Math.random() * WORDS.length)];
	    guessedWord = new char[word.length()];
	    for (int i = 0; i < word.length(); i++) {
	        guessedWord[i] = '_';
	    }
	    tries = 1;
	}

	    private void updateWordLabel() {
	        StringBuilder sb = new StringBuilder();
	        for (char c : guessedWord) {
	            sb.append(c).append(' ');
	        }
	        duma.setText(sb.toString());
	    }

	    private void opitileft() {
	        opiti.setText("Останали опити: " + (MAX_TRIES - tries));
	    }

	    private void processGuess() {
	        String bukvi = textField.getText();
	        if (bukvi.length() == 1 && Character.isLetter(bukvi.charAt(0))) {
	            char letter = bukvi.charAt(0);
	            boolean found = false;
	            for (int i = 0; i < word.length(); i++) {
	                if (word.charAt(i) == letter) {
	                    guessedWord[i] = letter;
	                    found = true;
	                }
	            }
	            if (!found) {
	            	besilo();
	                tries++;
	            }
	            updateWordLabel();
	            opitileft();
	            textField.setText("");
	            if (tries >= MAX_TRIES || String.valueOf(guessedWord).equals(word)) {
	                endGame();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Моля, въведи валидна буква!");
	        }
	    }

	    private void endGame() {
	        String message;
	        if (tries >= MAX_TRIES) {
	            message = "Опитай пак!:( Думата беше: " + word;
	        } else {
	            message = "Печелиш!:D";
	        }
	        JOptionPane.showMessageDialog(null, message);
	        setWord(); 
	        updateWordLabel();
	       opitileft();
	    
	    }
	    private void besilo() {
	        int imageIndex = tries + 1;
	       String image = "images/besilo" + imageIndex + ".png";
	        ImageIcon icon = new ImageIcon(image);
	       // man.setIcon(icon);
	       
	        }
	    

}
