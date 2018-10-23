import SinoIDL.*;

public class SinoImpl extends SinoPOA{

	/*
	 * Refer�ncias de cada GUI j� iniciadas.
	 * Toda a��o pode ser aplicada diretamente nessas GUI's
	 * atrav�s dos objetos abaixo:
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
