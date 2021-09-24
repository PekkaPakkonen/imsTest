import java.io.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class readUsersInfoFile {

    private String writeFile;
    private String readFile;
    private BufferedReader fileReader;
    private PrintWriter printWriter;
    private String data;
    private Pattern pattern;


    public readUsersInfoFile(String writeFile, String readFile) {
        this.writeFile = writeFile;
        this.readFile = readFile;
        try {
            printWriter = new PrintWriter(writeFile);
        } catch (FileNotFoundException e) {
            System.out.println("Write file not found!");
            e.printStackTrace();
        }

        try {
            fileReader = new BufferedReader(new FileReader(readFile));
        } catch (FileNotFoundException e) {
            System.out.println("Read file not found!");
            e.printStackTrace();
        }
    }

    public void addHeader() {
        printWriter.print("e-mail;password;marketing tab availability\n");
        printWriter.flush();
    }

    /*public String[] readCsv() throws IOException {
        fileReader = new BufferedReader(new FileReader(writeFile));
        System.out.println("Warning! fileReader is changed");
        while(fileReader.ready()) {
            String[] strings = fileReader.readLine().split(",");
        }
        return null;
    }*/

    public void readAndWriteToFile() throws IOException {
        StringBuilder csvLine = new StringBuilder();
        while(fileReader.ready()) {
            String str = fileReader.readLine();
            if (!str.contains("}") && (!str.contains("{"))) {
                if(str.contains("email")) {
                    String str1 = str.split("\\s+\"email\": \"")[1];
                    String str2 = str1.split("\",")[0];
                    csvLine.append(str2 +";");
                } else if(str.contains("password")) {
                    String str1 = str.split("\\s+\"ims2_password\": \"")[1];
                    if(!Objects.equals(str1,null)) {
                        try{
                            String str2 = str1.split("\",")[0];
                            csvLine.append(str2 +";\n");
                        } catch(ArrayIndexOutOfBoundsException e) {
                            e.printStackTrace();
                            System.out.println(str);
                        }
                    }
                    printWriter.write(csvLine.toString());
                    printWriter.flush();
                    csvLine.replace(0,csvLine.length(),"");
                }
            }
        }
    }

    public void setReadFile(String readFile) {
        this.readFile = readFile;
    }
}
