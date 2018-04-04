package relogio.principal;

import java.util.ArrayList;

import relogio.som.Audio;

public class Alarme {
	
	public String nome;
	public int hora;
	public int minuto;
	public int segundo;
	public String comentario;
	public Audio somDoAlarme;
	private boolean padrao = false;
	private boolean desligaPC = false;
	public static ArrayList<Alarme> todosAlarmes = new ArrayList<Alarme>();
	
	//acrescetar som do alarme

	public int getTotalSegundos() {
		int totalSegundos = 0;
		totalSegundos += 60 * 60 * hora;
		totalSegundos += 60 * minuto;
		totalSegundos += segundo;
		return totalSegundos;
	}
	
	public void adiar(int minutosAdiados) {
		minuto += minutosAdiados;
		
		System.out.println(nome + " foi atrasado para " + valorToSring(hora) + ":" + valorToSring(minuto) + ":" + valorToSring(segundo));
		
		if (this.minuto + minutosAdiados > 60) {
			hora++;						
			
			if (hora > 23) {
				hora = 23;
				minuto = 59;
				segundo = 59;
				return;
			}
			
			minuto = (minuto + minutosAdiados) - 60;
		}		
	}
	
	public String toString() {
		if (padrao)  {
			return this.nome;
		}
		
		return this.nome + "(" + valorToSring(hora) + ":" + valorToSring(minuto) + ":" + valorToSring(segundo) + ")";
	}
	
	public String valorToSring(int valor) {
		if (valor < 10) {
			return "0" + Integer.toString(valor);
		}	
		
		return Integer.toString(valor);
	}
	
	public Alarme(String nome, int hora, int minuto, int segundo, String comentario, Audio somDoAlarme, boolean desligaPC) {
		this.nome = nome;
		this.hora = hora;
		this.minuto = minuto;
		this.segundo = segundo;
		this.comentario = comentario;
		this.somDoAlarme = somDoAlarme;
		this.desligaPC = desligaPC;
		
		Alarme.todosAlarmes.add(this);
	}
	
	public Alarme(String nome, boolean isPadrao) {
		this.nome = nome;
		this.padrao = isPadrao;
		
	}
	
	public boolean getDesliga() {
		return desligaPC;
	}
	
	
	public void setDesliga(boolean desliga) {
		this.desligaPC = desliga;
	}
}
