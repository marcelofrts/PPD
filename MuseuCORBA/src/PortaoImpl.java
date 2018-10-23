import PortaoIDL.*;

public class PortaoImpl extends PortaoPOA {
	
	/*
	 * Referências de cada GUI já iniciadas.
	 * Toda ação pode ser aplicada diretamente nessas GUI's
	 * através dos objetos abaixo:
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
	 * Interface do portão 
	 * */	
	
	// OBS: Métodos entrar e sair não são utilizados, pois na própria GUI do portão
	//		visitantes entram e saem, guardando a info nela. Do contrário, teria que
	//		criar uma outra GUI Museu, onde ao entrar ou sair visitante, seria então usada a interface do portão.
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
