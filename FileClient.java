import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class FileClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java FileClient <server-host> <file-path>");
            return;
        }

        String serverHost = args[0];
        String filePath = args[1];

        try {
            Registry registry = LocateRegistry.getRegistry(serverHost, 1099);
            FileInterface stub = (FileInterface) registry.lookup("FileInterface");

            byte[] fileData = Files.readAllBytes(Paths.get(filePath));
            String fileName = new File(filePath).getName();

            stub.uploadFile(fileData, fileName);
            System.out.println("File " + fileName + " uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}