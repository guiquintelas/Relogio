package relogio.janelas;

import javax.swing.JTextArea;

public class PatchNote extends Janela{

	public PatchNote(String nome, int tamanhoX, int tamanhoY) {
		super(nome, tamanhoX, tamanhoY);
	}

	public void run() {
		if (aberto == false) {
			criarJanela();
			
			JTextArea notas = new JTextArea();
			notas.setBounds(10, 10, x - 20, y - 20);
			notas.setText("- Alarme aprimorado:" + "\n"
			+ "  Possivel a edição de Alarmes antigos." + "\n" 
			+ "  Adição da biblioteca de sons para Alarmes" + "\n" 
			+ "  Janela de Notificação que aparece quando o Alarme toca, dando a     opções de adiar o mesmo e finalizando o toque do alarme." + "\n"
			+ "  Hora maxima de casa alarme configurada para 23:59:59 e nao            99:99:99 presente na versão 1.6.1");
			notas.setLineWrap(true);
			notas.setBackground(janela.getBackground());
			janela.add(notas);
			notas.repaint();
		}
	}

}
