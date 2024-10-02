import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileInterface extends Remote {
   public byte[] downloadFile(String fileName) throws RemoteException;
   public void uploadFile(byte[] content, String fileName) throws RemoteException; // Correct method signature
}