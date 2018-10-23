import SinoIDL.*;

public class SinoImpl extends SinoPOA{

	/*
	 * Referências de cada GUI já iniciadas.
	 * Toda ação pode ser aplicada diretamente nessas GUI's
	 * através dos objetos abaixo:
	 * */
	
	GuardaGUI guarda;
	PortaoGUI portao;
	SinoGUI sino;
	
	public SinoImpl(GuardaGUI guarda) {
		this.guarda = guarda;
	}
	
	public SinoImpl(PortaoGUI portao) {
		this.portao = portao;
	}
	
	public SinoImpl(SinoGUI sino) {
		this.sino = sino;
	}
	
	/*
	 * Interface do sino 
	 * */	
	
	public void tocarSino () {	
		sino.tocarSino();
	}
	
	public void pararSino () {
		sino.pararSino();
	}
	
}
