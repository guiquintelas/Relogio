package relogio.principal;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;







import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import relogio.janelas.AlarmeJanela;
import relogio.janelas.PatchNote;
import relogio.janelas.Sobre;
import relogio.janelas.Velocidade;
import relogio.listeners.ClearOnClickListener;

public class Principal  {
	
	public static JFrame janela = new JFrame();
	
	private void carregarFonte(String fontName) throws FontFormatException, IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(fontName);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();		
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
	}

	public static void main(String[] args) throws FileNotFoundException, FontFormatException, IOException {
		
		Principal principal = new Principal();
		principal.carregarFonte("DS-DIGII.TTF");

		
		final JTextPane painel = new JTextPane();
		JTextPane amPMTextPane = new JTextPane();
		JPanel visor = new JPanel();
		
		final Relogio casio = new Relogio(painel, amPMTextPane, visor);
		
		LimiteTextBox limiteDuasHoras = new LimiteTextBox(2, true);
		LimiteTextBox limiteDoisMinutos = new LimiteTextBox(2, true);
		LimiteTextBox limiteDoisSegundos = new LimiteTextBox(2, true);
		//limiteBox é um document que labels usarao para limitar o numero maximo de caracteres, 
		//modificando o metodo q aplica um caractere no documento
		final Velocidade velocidadeJanela = new Velocidade("Velocidade", casio);
		final Sobre sobreJanela = new Sobre("Sobre");
		final AlarmeJanela alarmeJanela = new AlarmeJanela("Alarme", 440, 240);
		final PatchNote pacthNoteJanela = new PatchNote("Pacth Note", 400, 150);
		
		//Thread do relogio
		new Thread(casio).start();
		
		
		int x = 250;
		int y = 150;
		
		//somente para alterar o alinhamento de um JTextPane
		StyledDocument doc = painel.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		//janela = new JFrame();
		janela.setTitle("Relógio");
		janela.setSize(x, y);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setLayout(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setAlwaysOnTop(true);
		janela.setUndecorated(true);
		janela.setVisible(true);
		
		
		visor.setBounds(0, 30, x, y - 30);
		visor.setLayout(null);
		janela.add(visor);
		
		
		
		
		
		//inicio da barra de menu
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.setVisible(true);
		barraMenu.updateUI();
		janela.setJMenuBar(barraMenu);
		
		//inicio menuOpçoes
		JMenu menuOpçoes = new JMenu("Opções");
		menuOpçoes.setVisible(true);
		barraMenu.add(menuOpçoes);
		
		JMenuItem sinc	= new JMenuItem("Sincronizar");
		sinc.setVisible(true);
		menuOpçoes.add(sinc);
		sinc.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				//sincroniza a hora com o relogio do pc
				System.out.println("Relogio sincronizado");
				casio.definiçaoGeral(LocalDateTime.now().getHour(), LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond());
				casio.mostraHora();
				painel.setText(casio.horaCompleta);
				
			}
		});
		
		JMenuItem velocidade = new JMenuItem("Velocidade");
		velocidade.setVisible(true);
		menuOpçoes.add(velocidade);
		velocidade.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new Thread(velocidadeJanela).start();
				
				
			}
		});
		
		final JCheckBoxMenuItem amPMItem = new JCheckBoxMenuItem("AM/PM");
		menuOpçoes.add(amPMItem);
		amPMItem.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if (amPMItem.getState()) {
					casio.atualizaAMPM(false);
				} else {
					casio.atualizaAMPM(true);
				}
			}
		});
		
		JMenuItem alarme = new JMenuItem("Alarme");
		menuOpçoes.add(alarme);
		alarme.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new Thread(alarmeJanela).start();

				
			}
		});
		//final menuOpçoes
		
		//inicio de menu help
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setVisible(true);
		barraMenu.add(menuHelp);
		
		JMenuItem sobre = new JMenuItem("Sobre");
		menuHelp.add(sobre);
		sobre.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Thread(sobreJanela).start();
				
			}
		});
		
		JMenuItem pacthNote = new JMenuItem("Patch Note");
		menuHelp.add(pacthNote);
		pacthNote.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				new Thread(pacthNoteJanela).start();
				
			}
		});
		
		//final de menu help
		
		
		//final da barra de menu
		
		painel.setBounds(35, 10, 178, 50);
		painel.setVisible(true);
		painel.setFont(new Font("DS-Digital", Font.PLAIN, 50));
		painel.setEditable(false);
		visor.add(painel);
		
		
		amPMTextPane.setVisible(true);
		amPMTextPane.setBounds(208, 30, 30, 30);
		amPMTextPane.setEditable(false);
		amPMTextPane.setFont(new Font("DS-Digital", Font.PLAIN, 25));
		amPMTextPane.setBackground(visor.getBackground());
		visor.add(amPMTextPane);
		
		final JButton botaoParar = new JButton("Parar");
		botaoParar.setBounds(5, 5, 68, 20);
		botaoParar.setVisible(true);
		janela.add(botaoParar);
		botaoParar.updateUI();
		botaoParar.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				
				if (botaoParar.getText() == "Parar") {
					casio.setParado(true);
					botaoParar.setText("Voltar");
				} else {
					casio.setParado(false);
					botaoParar.setText("Parar");
					new Thread(casio).start();
				}
				
			}
		});
		
		final JTextField horasEditor = new JTextField();
		horasEditor.setBounds(150, 5, 25, 20);
		horasEditor.setVisible(true);
		horasEditor.setDocument(limiteDuasHoras);
		horasEditor.setText("HH");
		janela.add(horasEditor);
		horasEditor.updateUI();	
		horasEditor.addMouseListener(new ClearOnClickListener(horasEditor));
		
		
		final JTextField minutosEditor = new JTextField();
		minutosEditor.setBounds(180, 5, 25, 20);
		minutosEditor.setVisible(true);
		minutosEditor.setDocument(limiteDoisMinutos);
		minutosEditor.setText("MM");
		janela.add(minutosEditor);
		minutosEditor.updateUI();	
		minutosEditor.addMouseListener(new ClearOnClickListener(minutosEditor));	
		
		final JTextField segundosEditor = new JTextField();
		segundosEditor.setBounds(210, 5, 25, 20);
		segundosEditor.setVisible(true);
		segundosEditor.setDocument(limiteDoisSegundos);
		segundosEditor.setText("SS");
		janela.add(segundosEditor);
		segundosEditor.updateUI();	
		segundosEditor.addMouseListener(new ClearOnClickListener(segundosEditor));	
		
		JButton botaoSet = new JButton("Set");
		botaoSet.setVisible(true);
		botaoSet.setBounds(80, 5, 55, 20);
		janela.add(botaoSet);
		botaoSet.updateUI();
		botaoSet.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				//se os valores nao estao vazios, pegar a String correspondente e transforma-la em iNt
				if ((horasEditor.getText().isEmpty() == true) || (minutosEditor.getText().isEmpty() == true) || (segundosEditor.getText().isEmpty() == true)) {
					if (horasEditor.getText().isEmpty() == true) {
							horasEditor.setText("0");
						}
						
						if (minutosEditor.getText().isEmpty() == true) {
							minutosEditor.setText("0");
						}
						
						if (segundosEditor.getText().isEmpty() == true) {
							segundosEditor.setText("0");
						}
				
						
				}
				
				if (Integer.parseInt(horasEditor.getText()) < 24 && Integer.parseInt(minutosEditor.getText()) < 60 && Integer.parseInt(segundosEditor.getText()) < 60	) {
					casio.definiçaoGeral(Integer.parseInt(horasEditor.getText()), Integer.parseInt(minutosEditor.getText()), Integer.parseInt(segundosEditor.getText()));
					//casio.mostraHora();
					//painel.setText(casio.horaCompleta);
					painel.repaint();
				}
			}
			
			
				
			
		});
		
		
	}
}
