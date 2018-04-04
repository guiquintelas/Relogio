package relogio.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class ClearOnClickListener implements MouseListener {
	
	private JTextField text;
	private boolean primeiraVez = true;
	
	public ClearOnClickListener(JTextField textField) {
		this.text = textField;
	}

	public void mouseClicked(MouseEvent e) {
		if (primeiraVez) {
			System.out.println("clicou");
			text.setText("");
			primeiraVez = false;
		}
		
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	
	

}
