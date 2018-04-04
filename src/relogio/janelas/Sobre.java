package relogio.janelas;

import javax.swing.JTextArea;

public class Sobre extends Janela {

	public Sobre(String nome) {
		super(nome, 320, 100);
	}

	public void run() {
		if (aberto == false) {
			criarJanela();
			
			JTextArea copyright = new JTextArea();
			copyright.setVisible(true);
			copyright.setBounds(10, 10, x - 10, 20);
			copyright.setBackground(janela.getContentPane().getBackground());
			copyright.setEditable(false);
			copyright.setText("(c) Copyright Master_Ace company. All rights reserved.");
			janela.add(copyright);
			copyright.repaint();
			
			JTextArea versao = new JTextArea();
			versao.setVisible(true);
			versao.setBounds(10, 35, x - 10, 20);
			versao.setBackground(janela.getContentPane().getBackground());
			versao.setEditable(false);
			versao.setText("Versão: 2.0");
			janela.add(versao);
			versao.repaint();
			
			JTextArea numeroLinhas = new JTextArea(); 
			numeroLinhas.setVisible(true);
			numeroLinhas.setEditable(false);
			numeroLinhas.setText("Numero de linhas de código: 1.243");
			numeroLinhas.setBounds(10, 55, x - 10, 20);
			numeroLinhas.setBackground(janela.getContentPane().getBackground());
			janela.add(numeroLinhas);
			numeroLinhas.repaint();
			
			
			
		} else {
			
			janela.setVisible(true);
			
		}
		
	}
	

}