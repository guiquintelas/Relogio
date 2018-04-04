package relogio.principal;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import relogio.janelas.NotificaçaoAlarmeJanela;



public class Relogio implements Runnable {

	private int horas = 0;
	private int minutos = 0;
	private int segundos = 0;
	private int horasAMPM = 0;
	private int totalSegundos = 0;
	
	private boolean isAMPM = false;
	private JPanel visor;
	private JTextPane painel;
	private JTextPane amPM;
	public String horaCompleta = "00:00:00";	
	public boolean parado = false;
	public int velocidade = 1;
	
	private int corLetraInt = 1785;
	private int corPretoAzulEscuroInt = 0;//maximo 600
	private double corVermelhoInt = 0.0;
	private double corVerdeInt = 0.0;
	private double corAzulInt = 60.0;
	
	
	private void setTotalSegundos() {		
		totalSegundos = 0;
		totalSegundos += 60 * 60 * horas;
		totalSegundos += 60 * minutos;
		totalSegundos += segundos;

	}
	
	
	public void setParado(boolean parado) {
		this.parado = parado;
	}
	
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	
	
	// construtor
	public Relogio(JTextPane painel, JTextPane amPM, JPanel visor) { 
		this.painel = painel;
		this.amPM = amPM;
		this.visor = visor;
		
	}
	
	public void definiçaoGeral( int horas, int minutos, int segundos) {
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
		
		if (isAMPM) {
			atualizaAMPM(false);
		} else {
			atualizaAMPM();
		}
		
		mostraHora();
		setTotalSegundos();
		backupCores(totalSegundos);
		ajustarCorPainel();
		atualizaAMPM();
	}

	public void mostraHora() {	
		
		String horaAMPM = "0";
		String hora = "0";
		String minuto = "0";
		String segundo = "0";
		
		
		if (isAMPM) {
			System.out.print(horasAMPM + ":");
			horaAMPM = String.valueOf(horasAMPM);
		
		} else {
			if (horas < 10) {
				System.out.print("0" + horas + ":");
				hora = hora.concat(String.valueOf(horas));

			} else  {
				System.out.print(horas + ":");
				hora = String.valueOf(horas);
			}
		}
		

		if (minutos < 10) {
			System.out.print("0" + minutos + ":");
			minuto = minuto.concat(String.valueOf(minutos));
		} else {
			System.out.print(minutos + ":");
			minuto = String.valueOf(minutos);
		}

		if (segundos < 10) {
			System.out.print("0" + segundos);
			segundo = segundo.concat(String.valueOf(segundos));
		} else {
			System.out.print(segundos);
			segundo = String.valueOf(segundos);
		}

		System.out.println();
		
		if (isAMPM) {
			
			
			
			horaCompleta = horaAMPM + ":" + minuto + ":" + segundo;
			
		} else {
			horaCompleta = hora + ":" + minuto + ":" + segundo;
		}
		
		painel.setText(horaCompleta);
	}
	
	//metodo que edita a hora para amPm e atualiza o ampmTextPane para aparecer na tela, sem ser escondito pelo label
	public void atualizaAMPM(boolean desfazer) {
		if (desfazer == false) {
			isAMPM = true;
			
			amPM.setVisible(true);
			amPM.setText("AM");
			
			
			if (horas > 12) {
				horasAMPM = horas - 12;
				amPM.setText("PM");
			} else {
				horasAMPM = horas;
			}
			
			if (horas == 12) {
				amPM.setText("PM");
			}
			
			if (horas == 0) {
				horasAMPM = 12;
			}

		} else {
					
			amPM.setText("");
			isAMPM = false;
			amPM.setVisible(false);
			
		}
		
		mostraHora();//para quando se mudar a opção AMPM tendo o relogio parado a hora atualizar	
		amPM.setBackground(painel.getBackground());
		amPM.setForeground(painel.getForeground());
	}
	
	
	public void atualizaAMPM() {
		amPM.setVisible(false);	
		
		if (isAMPM) {	
			amPM.setVisible(true);
			if (horas > 12) {		
				amPM.setText("PM");	
				horasAMPM = horas - 12;
			} else {
				amPM.setText("AM");
				horasAMPM = horas;
			}
			
			if (horas == 12) {
				horasAMPM = horas;
				amPM.setText("PM");
			}
			
			if (horas == 0) {
				amPM.setText("AM");
				horasAMPM = 12;
			}
			
			amPM.setBackground(painel.getBackground());
			amPM.setForeground(painel.getForeground());	
		}
	
	}
	
	
	private void ajustarCorPainel() {
		if (horas == 5 && minutos >= 30) {
			//muda a cor da letra de branco para preto em um determinado periudo de tempo
			Color corLetra = new Color(corLetraInt/7, corLetraInt/7, corLetraInt/7);
			painel.setForeground(corLetra);
			
			if (minutos >= 30 && minutos <= 40) {
				//muda o fundo de preto (5:30) para azul escuro (5:40)
				Color pretoAzulEscuro = new Color(0, 0, corPretoAzulEscuroInt/10);
				painel.setBackground(pretoAzulEscuro);
			}
			
			if (minutos >= 40 && minutos <= 54) {
				System.out.println("vermelho" + corVermelhoInt + " Verde " + corVerdeInt + " Azul " + corAzulInt);
				Color azulEscuroLaranja = new Color((int)corVermelhoInt, (int)corVerdeInt, (int)corAzulInt);
				painel.setBackground(azulEscuroLaranja);
			}
			
			if (minutos >= 55 && minutos <= 59) {
				Color laranjaAmarelo = new Color((int)corVermelhoInt, (int)corVerdeInt, (int)corAzulInt);
				painel.setBackground(laranjaAmarelo);
			}		
			
			visor.setBackground(painel.getBackground());
			return;						
		}
		
		if (totalSegundos >= 64200 && totalSegundos <= 65999) {
			//muda a cor da letra de preto para branco em um determinado periudo de tempo
			Color corLetra = new Color(corLetraInt/7, corLetraInt/7, corLetraInt/7);
			painel.setForeground(corLetra);
			
			if (totalSegundos >= 64200 && totalSegundos <= 64499) {			
				Color amareloLaranja = new Color((int)corVermelhoInt, (int)corVerdeInt, (int)corAzulInt);
				System.out.println("vermelho " + corVermelhoInt + "verde " + corVerdeInt + " azul " + corAzulInt);
				painel.setBackground(amareloLaranja);
			}	
			
			if (totalSegundos >= 64500 && totalSegundos <= 64919) {
				Color laranjaAzulEscuro = new Color((int)corVermelhoInt, (int)corVerdeInt, (int)corAzulInt);
				System.out.println("vermelho " + corVermelhoInt + "verde " + corVerdeInt + " azul " + corAzulInt);
				painel.setBackground(laranjaAzulEscuro);
			}
			
			if (totalSegundos >= 65400 && totalSegundos <= 65999) {
				//muda o fundo de azul escuro (18:10) para preto (18:20)
				Color azulEscuroPreto = new Color(0, 0, corPretoAzulEscuroInt/10);
				painel.setBackground(azulEscuroPreto);
			}
			
			visor.setBackground(painel.getBackground());
			return;						
		}
		
		
		if (totalSegundos <= 21600 || totalSegundos >= 64800) {
			painel.setBackground(Color.BLACK);
			painel.setForeground(Color.WHITE);
		} else {
			painel.setBackground(new Color(0, 191, 255));
			painel.setForeground(Color.BLACK);
		}
		
		visor.setBackground(painel.getBackground());
	}
	
	

	public void passarTempo() {
		segundos++;

		if (segundos == 60) {
			segundos = 0;
			minutos++;
		}

		if (minutos == 60) {
			minutos = 0;
			horas++;
		}

		if (horas == 24) {
			horas = 0;
		}

	}
	
	private void nascerPorDoSol() {
		
		//reset da mudança da cor preta para azul escura, nao sendo nescessario a checagem em um periodo 
		if (totalSegundos == 19799) {
			corPretoAzulEscuroInt = 0;
			corLetraInt = 1785;
			this.corAzulInt= 60;
			this.corVermelhoInt = 0;
			this.corVerdeInt = 0;	
		}
		
		if (totalSegundos == 64199) {
			corPretoAzulEscuroInt = 600;
			corLetraInt = 0;
			this.corAzulInt= 0;
			this.corVermelhoInt = 255;
			this.corVerdeInt = 215;
		}
		
		
		
		//nascer do sol
		if (totalSegundos > 19800 && totalSegundos < 21599) {
			
			if (corLetraInt > 0) {
				corLetraInt--;
			}
			
			if (corPretoAzulEscuroInt < 600) {
				corPretoAzulEscuroInt++;
			}
			
			if (totalSegundos >= 20400 && totalSegundos <= 20879) {
				corVermelhoInt = corVermelhoInt + 0.53125;
				corVerdeInt = corVerdeInt + 0.2916;
				corAzulInt = corAzulInt - 0.125;
			}
			
			if (totalSegundos >= 20880 && totalSegundos <= 21299) {
				//corVermelhoInt = corVermelhoInt;
				corVerdeInt = corVerdeInt + 0.17857142857;
				//corAzulInt = corAzulInt;
			}
			
			if (totalSegundos >= 21300) {
				corVermelhoInt = 0;
				corVerdeInt = 191;				
				corAzulInt = 255;

			}
			
		}
		
		//por do sol
		if (totalSegundos > 64200 && totalSegundos < 65999) {
			
			if (totalSegundos >= 64200 && totalSegundos <= 64499) {
				//corVermelhoInt = corVermelhoInt;
				corVerdeInt = corVerdeInt - 0.17857142857;
				//corAzulInt = corAzulInt;
			}
			
			if (totalSegundos >= 64500 && totalSegundos <= 64919) {
				corVermelhoInt = corVermelhoInt - 0.53125;
				corVerdeInt = corVerdeInt - 0.2916;
				corAzulInt = corAzulInt + 0.125;
			}

	
			if (corPretoAzulEscuroInt > 0 && totalSegundos >= 65400) {
				corPretoAzulEscuroInt--;
			}
			
			if (corLetraInt < 1785) {
				corLetraInt++;
			}

		}		
		
	};
	
	//simulação para obter os valores das cores em determinado tempo, aplica-se quando o usuario seta o tempo
	private void backupCores(int TotalSegundosAtuais) {
		int totalSegundos = 0;
		int corLetraInt = 1785;
		int corPretoAzulEscuroInt = 0;//maximo 1200
		double corVermelhoInt = 0.0;
		double corVerdeInt = 0.0;
		double corAzulInt = 60.0;
		
		if (TotalSegundosAtuais > 61200) {
			corVermelhoInt = 0.0;
			corVerdeInt = 191.0;
			corAzulInt = 255.0;
			
		}
		
		while (totalSegundos < TotalSegundosAtuais) {
			
			if (totalSegundos > 19800 && totalSegundos < 21599) {
							
				if (corLetraInt > 0) {
					corLetraInt--;
				}
				
				if (corPretoAzulEscuroInt < 600) {
					corPretoAzulEscuroInt++;
				}
				
				if (totalSegundos >= 20400 && totalSegundos <= 20879) {
					corVermelhoInt = corVermelhoInt + 0.53125;
					corVerdeInt = corVerdeInt + 0.2916;
					corAzulInt = corAzulInt - 0.125;
				}
				
				if (totalSegundos >= 20880 && totalSegundos <= 21299) {
					//corVermelhoInt = corVermelhoInt;
					corVerdeInt = corVerdeInt + 0.17857142857;
					//corAzulInt = corAzulInt;
				}
				
				if (totalSegundos >= 21300) {
					corVermelhoInt = 0;
					corVerdeInt = 191;				
					corAzulInt = 255;

				}
			}
			
			if (totalSegundos == 64199) {
				corPretoAzulEscuroInt = 600;
				corLetraInt = 0;
				corAzulInt= 0;
				corVermelhoInt = 255;
				corVerdeInt = 215;
			}
			
			if (totalSegundos > 64200 && totalSegundos < 65999) {
				
				if (totalSegundos >= 64200 && totalSegundos <= 64499) {
					//corVermelhoInt = corVermelhoInt;
					corVerdeInt = corVerdeInt - 0.17857142857;
					//corAzulInt = corAzulInt;
				}
				
				if (totalSegundos >= 64500 && totalSegundos <= 64919) {
					corVermelhoInt = corVermelhoInt - 0.53125;
					corVerdeInt = corVerdeInt - 0.2916;
					corAzulInt = corAzulInt + 0.125;
				}

		
				if (corPretoAzulEscuroInt > 0 && totalSegundos >= 65400) {
					corPretoAzulEscuroInt--;
				}
				
				if (corLetraInt < 1785) {
					corLetraInt++;
				}

			}
			
			totalSegundos++;
		}
		
		this.corLetraInt = corLetraInt;
		this.corPretoAzulEscuroInt = corPretoAzulEscuroInt;
		this.corVermelhoInt = corVermelhoInt;
		this.corVerdeInt = corVerdeInt;		
		this.corAzulInt = corAzulInt;
		
	}
	
	private void checaAlarme() {
		if (Alarme.todosAlarmes != null) {
			for (int x = 0; x < Alarme.todosAlarmes.size(); x ++) {
				if (Alarme.todosAlarmes.get(x).getTotalSegundos() == this.totalSegundos) {
					
					if (Alarme.todosAlarmes.get(x).getDesliga()) {
							Runtime runtime = Runtime.getRuntime();
							try {
								runtime.exec("shutdown -s -t 0");
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.exit(0);
						
					}
					
					Alarme.todosAlarmes.get(x).somDoAlarme.play();
					
					NotificaçaoAlarmeJanela janelaNoti = new NotificaçaoAlarmeJanela(Alarme.todosAlarmes.get(x));
					
					new Thread(janelaNoti).start();
				}
			}
		}
		
	}

	public synchronized void run() {
		
		while (parado == false) {
			passarTempo();
			setTotalSegundos();	
			ajustarCorPainel();
			atualizaAMPM();
			mostraHora();	
			nascerPorDoSol();
			checaAlarme();
			
			try {
				Thread.sleep(1000 / velocidade);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
	}


	

}
