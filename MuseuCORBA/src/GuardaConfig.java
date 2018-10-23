import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class GuardaConfig {
	public static void main(String args[]){
		 try{
			ORB orb = ORB.init(args,null); 			
			org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");	
			POA rootPOA = POAHelper.narrow(objPoa);		
			org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
			NamingContext naming = NamingContextHelper.narrow(obj);

			GuardaGUI gg = new GuardaGUI();
			GuardaImpl guarda = new GuardaImpl(gg);
			gg.setVisible(true);
			org.omg.CORBA.Object objRefGuarda = rootPOA.servant_to_reference(guarda);
			NameComponent[] nameGuarda = {new NameComponent("Guarda","TipoGuarda")};
			naming.rebind(nameGuarda,objRefGuarda);
			rootPOA.the_POAManager().activate();

			System.out.println("GuardaGUI...");

			orb.run();

		   }catch (Exception ex){
				System.out.println("Erro");
				ex.printStackTrace();}
		  }
}
