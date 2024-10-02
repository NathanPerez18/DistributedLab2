import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

public class FileImpl implements FileInterface {

   public FileImpl() {
      try {
         UnicastRemoteObject.exportObject(this, 0);
      } catch (RemoteException e) {
         System.out.println("FileImpl: " + e.getMessage());
         e.printStackTrace();
      }
   }

   public byte[] downloadFile(String fileName) {
      try {
         // Log the client's IP address
         String clientHost = RemoteServer.getClientHost();
         System.out.println("Client IP address: " + clientHost);

         // Log the requested file name
         System.out.println("File requested: " + fileName);

         File file = new File(fileName);
         if (!file.exists()) {
            System.out.println("File does not exist: " + fileName);
            return null;
         }

         byte buffer[] = new byte[(int) file.length()];
         BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
         input.read(buffer, 0, buffer.length);
         input.close();
         return buffer;
      } catch (Exception e) {
         System.out.println("FileImpl: " + e.getMessage());
         e.printStackTrace();
         return null;
      }
   }

   public void uploadFile(byte[] content, String fileName) {
      try {
         // Log the start of the file upload
         System.out.println("Starting file upload: " + fileName);

         File file = new File(fileName);
         BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
         output.write(content, 0, content.length);
         output.flush();
         output.close();

         // Log the successful completion of the file upload
         System.out.println("File upload completed: " + fileName);
      } catch (Exception e) {
         System.out.println("FileImpl: " + e.getMessage());
         e.printStackTrace();
      }
   }
}