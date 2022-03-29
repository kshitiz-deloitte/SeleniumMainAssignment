import java.io.*;

public class FileHandler {
    String[] data;
    //Reads Customer data from File and store the data in String array.
    FileHandler(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        FileReader fr = new FileReader(file);

        try (BufferedReader br = new BufferedReader(fr)) {
            String DELIMITER = ",";
            String line;
            while ((line = br.readLine()) != null) {

                this.data =  line.split(DELIMITER);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //Returns the data of Customer
    public String[] getData() {
        return data;
    }
    //Sets data of Customer
    public void setData(String[] data) {
        this.data = data;
    }
}
