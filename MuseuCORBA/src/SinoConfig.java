import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class SinoConfig {
	public static void main(String args[]){
		 try{
			ORB orb = ORB.init(args,null); 			
			org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");	
			POA rootPOA = POAHelper.narrow(objPoa);		
			org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService");
			NamingContext naming = NamingContextHelper.narrow(obj);

			SinoGUI sg = new SinoGUI();
			SinoImpl sino = new SinoImpl(sg);
			sg.setVisible(true);
			org.omg.CORBA.Object objRefSino = rootPOA.servant_to_reference(sino);
			NameComponent[] nameSino = {new NameComponent("Sino","TipoSino")};
			naming.rebind(nameSino,objRefSino);
			rootPOA.the_POAManager().activate();

			System.out.println("SinoGUI...");

			orb.run();

		   }catch (Exception ex){
				System.out.println("Erro");
				ex.printStackTrace();}
		  }
}
