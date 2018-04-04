package relogio.janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import relogio.principal.Alarme;
import relogio.principal.Principal;

public class NotificaçaoAlarmeJanela extends Janela {
	private Alarme alarme;

	public NotificaçaoAlarmeJanela(Alarme alarme) {		
		super("O alarme " + alarme.nome + " despertou!", 300, 140);
		this.alarme = alarme;
	}

	public void run() {
		criarJanela();
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		janela.setLocation(Principal.janela.getX(), Principal.janela.getY());
		janela.setAutoRequestFocus(true);
		janela.setAlwaysOnTop(true);
		
		Principal.janela.setEnabled(false);
		
		JTextArea comentario = new JTextArea(alarme.comentario);
		comentario.setLineWrap(true);
		comentario.setBounds(10, 5, 280, 80);
		comentario.setBackground(janela.getBackground());
		comentario.setEditable(false);
		janela.add(comentario);
		
		JButton botaoOk = new JButton("OK");
		botaoOk.setBounds(30, 90, 60, 20);
		janela.add(botaoOk);
		botaoOk.repaint();
		botaoOk.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				janela.setVisible(false);
				Principal.janela.setEnabled(true);
				alarme.somDoAlarme.stop();
				Principal.janela.requestFocus();
				
			}
		});
		
		JButton botaoAdiar = new JButton("Adiar 5 minutos");
		botaoAdiar.setBounds(95, 90, 160, 20);
		janela.add(botaoAdiar);
		botaoAdiar.repaint();
		botaoAdiar.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				janela.setVisible(false);
				Principal.janela.setEnabled(true);
				alarme.somDoAlarme.stop();
				alarme.adiar(5);
				Principal.janela.requestFocus();
				
			}
		});
	}

}
