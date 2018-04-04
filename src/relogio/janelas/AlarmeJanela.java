package relogio.janelas;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import relogio.listeners.ClearOnClickListener;
import relogio.principal.Alarme;
import relogio.principal.LimiteTextBox;
import relogio.som.Audio;

public class AlarmeJanela extends Janela{

	public AlarmeJanela(String nome, int tamanhoX, int tamanhoY) {
		super(nome, tamanhoX, tamanhoY);
	}
	
	public JTextField editorNome;
	public JTextField horasEditor;
	public JTextField minutosEditor;
	public JTextField segundosEditor;
	public JTextArea textAreaComentario;
	public JButton botaoAdd;
	public JButton butaoDel;
	public Audio ultimoAlarmeSelecionado;
	public Checkbox desligaPCCheck;
	
	//para diferenciar alarmes com nomes iguais, nome1, nome2, nome3...
	private static int contNomeIgual = 1;

	public void run() {
		if (aberto == false) {
			
			LimiteTextBox limiteDuasHoras = new LimiteTextBox(2, true);
			LimiteTextBox limiteDoisMinutos = new LimiteTextBox(2, true);
			LimiteTextBox limiteDoisSegundos = new LimiteTextBox(2, true);
			
			criarJanela();
			
			//abrindo audios
			Audio alarmeCarro = new Audio("Alarme de Carro.wav");
			Audio alarmeCachorro = new Audio("Cachorro Latindo.wav");
			Audio alarmeInstinto = new Audio("Instinto Natural.wav");
			Audio alarmeMA = new Audio("Master Ace.wav");
			Audio alarmeGritoRei = new Audio("O Grito do Rei.wav");
			Audio alarmeSirene = new Audio("Sirene Policial.wav");
			Audio alarmeMae = new Audio("Sua Mãe.wav");
			
			
			
			
			//inicio editor de nome alrme
			JLabel labelNomeAlarme = new JLabel("Nome do Alarme: ");
			labelNomeAlarme.setBounds(5, 5, 100, 30);
			janela.add(labelNomeAlarme);
			
			editorNome = new JTextField("Nome");
			editorNome.setBounds(150, 10, 100, 20);
			janela.add(editorNome);
			editorNome.addMouseListener(new ClearOnClickListener(editorNome));
			
			//fim editor de nome alrme
			
			//inicio editor de horas alrme
			JLabel labelHoraAlarme = new JLabel("Hora do Alarme: ");
			labelHoraAlarme.setBounds(5, 35, 95, 30);
			janela.add(labelHoraAlarme);
			
			horasEditor = new JTextField();
			horasEditor.setBounds(165, 40, 25, 20);
			horasEditor.setVisible(true);
			horasEditor.setDocument(limiteDuasHoras);
			horasEditor.setText("HH");
			janela.add(horasEditor);
			horasEditor.updateUI();			
			horasEditor.addMouseListener(new ClearOnClickListener(horasEditor));
			
			
			minutosEditor = new JTextField();
			minutosEditor.setBounds(195, 40, 25, 20);
			minutosEditor.setVisible(true);
			minutosEditor.setDocument(limiteDoisMinutos);
			minutosEditor.setText("MM");
			janela.add(minutosEditor);
			minutosEditor.updateUI();		
			minutosEditor.addMouseListener(new ClearOnClickListener(minutosEditor));
			
			segundosEditor = new JTextField();
			segundosEditor.setBounds(225, 40, 25, 20);
			segundosEditor.setVisible(true);
			segundosEditor.setDocument(limiteDoisSegundos);
			segundosEditor.setText("SS");
			janela.add(segundosEditor);
			segundosEditor.updateUI();		
			segundosEditor.addMouseListener(new ClearOnClickListener(segundosEditor));
			//fim de editor de horas alarme
			
			//inicio do ediotr de som
			JLabel labelSom = new JLabel("Som do Alarme:");
			labelSom.setBounds(5, 65, 100, 30);
			janela.add(labelSom);
			
			//botao esta aqui pois é nescessario troca-lo quando o ultimo audio selecionado ainda estiver sendo tocado
			//entao assim o botao permanecera como "Parar" msm nao tendo audio tocando
			final JButton botaoTocarAudio = new JButton("Tocar");
			final JComboBox<Audio> listaSomAlarme = new JComboBox<Audio>();
			listaSomAlarme.setBounds(110, 70, 140, 20);
			janela.add(listaSomAlarme);
			listaSomAlarme.addItem(alarmeCarro);
			listaSomAlarme.addItem(alarmeCachorro);
			listaSomAlarme.addItem(alarmeMA);
			listaSomAlarme.addItem(alarmeGritoRei);
			listaSomAlarme.addItem(alarmeSirene);
			listaSomAlarme.addItem(alarmeMae);
			listaSomAlarme.addItem(alarmeInstinto);
			ultimoAlarmeSelecionado = (Audio)listaSomAlarme.getSelectedItem();
			listaSomAlarme.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					ultimoAlarmeSelecionado.stop();
					ultimoAlarmeSelecionado = (Audio)listaSomAlarme.getSelectedItem();
					
					botaoTocarAudio.setText("Tocar");
				}
			});
			
			
			
			botaoTocarAudio.setBounds(180, 95, 70, 20);
			janela.add(botaoTocarAudio);
			botaoTocarAudio.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					if (botaoTocarAudio.getText().matches("Tocar")) {
						((Audio) listaSomAlarme.getSelectedItem()).play();
						botaoTocarAudio.setText("Parar");
					} else {
						((Audio) listaSomAlarme.getSelectedItem()).stop();
						botaoTocarAudio.setText("Tocar");
					}
									
				}
			});
			
			desligaPCCheck = new Checkbox();
			desligaPCCheck.setFocusable(false);
			desligaPCCheck.setBounds(75, 87, 30, 30);
			desligaPCCheck.setVisible(true);
			janela.add(desligaPCCheck);
			
			JLabel textoDesligaPC = new JLabel("Desliga PC:");
			textoDesligaPC.setBounds(5, 87, 70, 30);
			janela.add(textoDesligaPC);
			
			//fim do ediotr de som
			
			//inicio editor de comentario do alarme
			
			JLabel labelComentario = new JLabel("Comentário: ");
			labelComentario.setBounds(5, 110, 100, 20);
			janela.add(labelComentario);
			
			textAreaComentario = new JTextArea();
			textAreaComentario.setBounds(5, 130, 245, 50);
			textAreaComentario.setLineWrap(true);
			janela.add(textAreaComentario);
			
			JScrollPane sbTextArea = new JScrollPane(textAreaComentario);
			sbTextArea.setBounds(5, 130, 245, 50);
			janela.add(sbTextArea);
			//fim editor de comentario do alarme
			
			//inicio lista alarmes
			JPanel painelDaLista = new JPanel(new BorderLayout()); 
			painelDaLista.setBounds(270, 10, 155, 200);
			painelDaLista.setVisible(true);
			painelDaLista.setBackground(Color.BLACK);
			painelDaLista.setOpaque(true);
			janela.add(painelDaLista);
			
			final DefaultListModel<Alarme> listModel = new DefaultListModel<Alarme>();
			listModel.addElement(new Alarme("Criar Novo", true));
			listModel.addElement(new Alarme("Almoço",13,30,00, "Arroz, feijão, bife e batata frita", alarmeMA, false));
			listModel.addElement(new Alarme("Hora Especial",20,30,00, "Bater no Daniel, bater muito! Se ele chorar  continue...", alarmeGritoRei, false));
			
			
			
			final JList<Alarme> listaAlarmes = new JList<Alarme>(listModel);
			listaAlarmes.setVisible(true);
			listaAlarmes.setBounds(0, 0, 155, 200);
			listaAlarmes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaAlarmes.setLayoutOrientation(JList.VERTICAL);
			listaAlarmes.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
			painelDaLista.add(listaAlarmes);
			listaAlarmes.addListSelectionListener(new ListSelectionListener() {			
				public void valueChanged(ListSelectionEvent e) {					
					int index = listaAlarmes.getSelectedIndex();
					if (listaAlarmes.getSelectedIndex() < 0) {
						return;
					}
						
					
					if (index != 0) {
						
						Alarme alarmeSelecionado = listModel.get(index);	
						editorNome.setText(alarmeSelecionado.nome);
						horasEditor.setText(alarmeSelecionado.valorToSring(alarmeSelecionado.hora));
						minutosEditor.setText(alarmeSelecionado.valorToSring(alarmeSelecionado.minuto));
						segundosEditor.setText(alarmeSelecionado.valorToSring(alarmeSelecionado.segundo));
						textAreaComentario.setText(alarmeSelecionado.comentario);
						listaSomAlarme.setSelectedItem(alarmeSelecionado.somDoAlarme);
						desligaPCCheck.setState(alarmeSelecionado.getDesliga());
					
						butaoDel.setEnabled(true);
						botaoAdd.setText("Edit");
					} else {
						
						editorNome.setText("Nome");
						horasEditor.setText("");
						minutosEditor.setText("");
						segundosEditor.setText("");
						textAreaComentario.setText("");
							
						butaoDel.setEnabled(false);
						botaoAdd.setText("Add");
					}
					
				}
			});
			
			JScrollPane sp = new JScrollPane(listaAlarmes);
			painelDaLista.add(sp, BorderLayout.CENTER);
			// fim lista alarmes
			
			
			botaoAdd = new JButton("Add");
			botaoAdd.setBounds(20, 185, 60, 20);
			botaoAdd.setVisible(true);
			botaoAdd.setEnabled(true);
			janela.add(botaoAdd);
			botaoAdd.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {															
					
					if (botaoAdd.getText().matches("Edit")) {
						Alarme alarmeSelecionado = listModel.get(listaAlarmes.getSelectedIndex());
						
						if (Integer.parseInt(horasEditor.getText()) > 23 || Integer.parseInt(minutosEditor.getText()) > 59 
								|| Integer.parseInt(segundosEditor.getText()) > 59) {
							return;
						}
						
						alarmeSelecionado.nome = editorNome.getText();
						alarmeSelecionado.hora = Integer.parseInt(horasEditor.getText());
						alarmeSelecionado.minuto = Integer.parseInt(minutosEditor.getText());
						alarmeSelecionado.segundo = Integer.parseInt(segundosEditor.getText());
						alarmeSelecionado.comentario = textAreaComentario.getText();
						alarmeSelecionado.setDesliga(desligaPCCheck.getState());
						listaAlarmes.repaint();
						return;
					}
					
					int hora = 0;
					int min = 0;
					int seg = 0;
					
					
					if (!horasEditor.getText().matches("HH") && !horasEditor.getText().isEmpty()) {
						hora = Integer.parseInt(horasEditor.getText());
						System.out.println("passou");
					}
					
					if (!minutosEditor.getText().matches("MM") && !minutosEditor.getText().isEmpty()) {
						min = Integer.parseInt(minutosEditor.getText());
					}
					
					if (!segundosEditor.getText().matches("SS") && !segundosEditor.getText().isEmpty()) {
						seg = Integer.parseInt(segundosEditor.getText());
					}
					
					Alarme alarme = new Alarme(editorNome.getText(), hora,
							min , seg,
							textAreaComentario.getText(), (Audio)listaSomAlarme.getSelectedItem(), desligaPCCheck.getState());		
					
					if (alarme.hora > 23 || alarme.minuto > 59 || alarme.segundo > 59) {
						return;
					}
					
					for (int x = 0; x < listModel.getSize(); x++) {
						//se o nome ja tiver sido usado retornara o metodo antes do adição do alarme
						if (alarme.nome.matches(listModel.get(x).nome)) {
							alarme.nome = alarme.nome.concat(Integer.toString(contNomeIgual));
							contNomeIgual++;
							x = 0;
							continue;
						}
						
						//se a mesma hora ja tiver sido usada o metodo antes do adição do alarme
						if (alarme.getTotalSegundos() == listModel.get(x).getTotalSegundos()) {
							
							return;
						}
					}				
					
					listModel.addElement(alarme);
					listaAlarmes.setSelectedIndex(listModel.getSize() - 1);
				}
			});
			
			
			JButton butaoClear = new JButton("Clear");
			butaoClear.setBounds(90, 185, 70, 20);
			butaoClear.setVisible(true);
			janela.add(butaoClear);
			butaoClear.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					editorNome.setText("");
					textAreaComentario.setText("");
					horasEditor.setText("");
					minutosEditor.setText("");
					segundosEditor.setText("");
				}
			});
			
			butaoDel = new JButton("Del");
			butaoDel.setBounds(170, 185, 60, 20);
			butaoDel.setVisible(true);
			butaoDel.setEnabled(false);
			janela.add(butaoDel);
			butaoDel.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int index = listaAlarmes.getSelectedIndex();
					Alarme.todosAlarmes.remove(listModel.getElementAt(index));
					listModel.remove(index);
					listaAlarmes.setSelectedIndex(0);
					butaoDel.setEnabled(false);
					
					editorNome.setText("");
					textAreaComentario.setText("");
					horasEditor.setText("");
					minutosEditor.setText("");
					segundosEditor.setText("");
				}
			});
			
			
			
			//correção do problema de criação da janela. swing nao é thread safe :c
			janela.setVisible(false);
			janela.setVisible(true);
			
			//loop que enquando a janela esta visivel checa pelos campos do nome, horas, minutos e segundos
			//para ativar ou nao o botao Add
			ChecaEspaços checaEspaços = new ChecaEspaços();
			new Thread(checaEspaços).start();
			
		} else {
			
			janela.setVisible(true);
			
			ChecaEspaços checaEspaços = new ChecaEspaços();
			new Thread(checaEspaços).start();
			
		}
		
	}
	
	public class ChecaEspaços implements Runnable {
		
		public void run() {
			while (janela.isVisible()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (editorNome.getText().isEmpty()) {
					botaoAdd.setEnabled(false);
					continue;
				}
				

				
				botaoAdd.setEnabled(true);
			}
			
		}
		
	}

}


