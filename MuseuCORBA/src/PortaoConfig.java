import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class PortaoConfig {
	public static void main(String args[]){
		 try{
			ORB orb = ORB.init(args,null); 			
			org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");	
			POA rootPOA = POAHelper.narrow(objPoa);		
			org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
			NamingContext naming = NamingContextHelper.narrow(obj);

			PortaoGUI pg = new PortaoGUI();
			PortaoImpl portao = new PortaoImpl(pg);
			pg.setVisible(true);
			org.omg.CORBA.Object objRefPortao = rootPOA.servant_to_reference(portao);
			NameComponent[] namePortao = {new NameComponent("Portao","TipoPortao")};
			naming.rebind(namePortao,objRefPortao);
			rootPOA.the_POAManager().activate();

			System.out.println("PortãoGUI...");

			orb.run();

		   }catch (Exception ex){
				System.out.println("Erro");
				ex.printStackTrace();}
		  }
}
