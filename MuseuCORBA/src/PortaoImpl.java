import PortaoIDL.*;

public class PortaoImpl extends PortaoPOA {
	
	/*
	 * Refer�ncias de cada GUI j� iniciadas.
	 * Toda a��o pode ser aplicada diretamente nessas GUI's
	 * atrav�s dos objetos abaixo:
	 * */
	
	GuardaGUI guarda;
	PortaoGUI portao;
	SinoGUI sino;
	
	public PortaoImpl(GuardaGUI guarda) {
		this.guarda = guarda;
	}
	
	public PortaoImpl(PortaoGUI portao) {
		this.portao = portao;
	}
	
	public PortaoImpl(SinoGUI sino) {
		this.sino = sino;
	}
	
	/*
	 * Interface do port�o 
	 * */	
	
	// OBS: M�todos entrar e sair n�o s�o utilizados, pois na pr�pria GUI do port�o
	//		visitantes entram e saem, guardando a info nela. Do contr�rio, teria que
	//		criar uma outra GUI Museu, onde ao entrar ou sair visitante, seria ent�o usada a interface do port�o.
	public void entrarVisitante () {
		guarda.entrar();
	}
	
	public void sairVisitante () {
		guarda.sair();
	}
	
	//
	//
	
	public void amanhecer () {
		portao.isDia();
	}
	
	public void anoitecer () {
		portao.isNoite();
	}
	
}
