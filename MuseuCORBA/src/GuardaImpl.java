import GuardaIDL.*;

public class GuardaImpl extends GuardaPOA{
	
	/*
	 * Refer�ncias de cada GUI j� iniciadas.
	 * Toda a��o pode ser aplicada diretamente nessas GUI's
	 * atrav�s dos objetos abaixo:
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
