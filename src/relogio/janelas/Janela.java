package relogio.janelas;

import javax.swing.JFrame;

import relogio.principal.Principal;

public abstract class Janela implements Runnable {
	
	protected String nome;
	protected boolean aberto = false;
	protected int x;
	protected int y;
	protected JFrame janela = new JFrame(nome);
	
	
	public Janela(String nome, int tamanhoX, int tamanhoY) {
		this.nome = nome;
		this.x = tamanhoX;
		this.y = tamanhoY;
	}
	
	protected void criarJanela() {		
	
		janela.setTitle(nome);
		janela.setVisible(true);
		janela.setBounds(Principal.janela.getX() - ((x - Principal.janela.getWidth())/2), Principal.janela.getY() + Principal.janela.getHeight() + 15, x, y);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		janela.setLayout(null);
		aberto = true;
	}

}
