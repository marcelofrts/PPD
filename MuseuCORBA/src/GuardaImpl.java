import GuardaIDL.*;

public class GuardaImpl extends GuardaPOA{
	
	/*
	 * Referências de cada GUI já iniciadas.
	 * Toda ação pode ser aplicada diretamente nessas GUI's
	 * através dos objetos abaixo:
	 * */
	
	GuardaGUI guarda;
	PortaoGUI portao;
	SinoGUI sino;
	
	public GuardaImpl(GuardaGUI guarda) {
		this.guarda = guarda;
	}
	
	public GuardaImpl(PortaoGUI portao) {
		this.portao = portao;
	}
	
	public GuardaImpl(SinoGUI sino) {
		this.sino = sino;
	}
	
	/*
	 * Interface do guarda 
	 * */	
	
	public void entrarVisitante () {
		guarda.entrar();
	}
	
	public void sairVisitante () {
		guarda.sair();
	}

}
