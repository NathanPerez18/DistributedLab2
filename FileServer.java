import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FileServer {
   public static void main(String args[]) {
      try {
         FileImpl fileImpl = new FileImpl();
         Registry registry = LocateRegistry.createRegistry(1099);
         registry.rebind("FileInterface", fileImpl); // Ensure this line is correct
         System.out.println("FileServer is ready.");
      } catch (Exception e) {
         System.out.println("FileServer: " + e.getMessage());
         e.printStackTrace();
      }
   }
}