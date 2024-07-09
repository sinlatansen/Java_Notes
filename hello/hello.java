import java.io.File; // Import the File class

public class Hello {
   public static void main(String[] args) {
      File f = new File("tmp");
      if (f.delete()) {
         System.out.println("File deleted successfully");
      } else {
         System.out.println("Failed to delete the file");
      }
   }
}