package relogio.janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import relogio.listeners.ClearOnClickListener;
import relogio.principal.LimiteTextBox;
import relogio.principal.Relogio;

public class Velocidade extends Janela{

	
	private Relogio relogio;
	
	
	//construtor
	public Velocidade(String nome, Relogio relogio) {
		super(nome, 265, 70);
		this.relogio = relogio;
		
	}
	
	
	

	public synchronized void run() {
		
		LimiteTextBox limiteTresDigitos = new LimiteTextBox(3, true);
		
		if (aberto == false) {
			
			criarJanela();
			
			JTextArea textoVelocidade = new JTextArea();
			textoVelocidade.setBounds(105, 11, 150, 20);
			textoVelocidade.setVisible(true);
			textoVelocidade.setEditable(false);
			textoVelocidade.setBackground(janela.getContentPane().getBackground());
			textoVelocidade.setText("vezes a velocidade normal");
			janela.add(textoVelocidade);
			
			
			final JTextField textBoxVelocidade = new JTextField();
			textBoxVelocidade.setBounds(70, 10, 30, 20);
			textBoxVelocidade.setVisible(true);
			textBoxVelocidade.setDocument(limiteTresDigitos);
			textBoxVelocidade.setText(" X");
			janela.add(textBoxVelocidade);
			textBoxVelocidade.updateUI();
			textBoxVelocidade.addMouseListener(new ClearOnClickListener(textBoxVelocidade));
			
			JButton botaoSet = new JButton("Set");
			botaoSet.setVisible(true);
			botaoSet.setBounds(5, 10, 55, 20);
			janela.add(botaoSet);
			botaoSet.updateUI();
			botaoSet.addActionListener(new ActionListener() {			
				public void actionPerformed(ActionEvent e) {
					if (textBoxVelocidade.getText().isEmpty() == false) {
						if (Integer.parseInt(textBoxVelocidade.getText()) != 0) {
							relogio.setVelocidade(Integer.parseInt(textBoxVelocidade.getText()));
						}
					}
					
					
				}
			});
			
			
			
		} else {
			
			janela.setVisible(true);
		}
		
		
		
		
	}

}
