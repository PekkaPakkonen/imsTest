public class Main {
    public static void main(String[] args) throws Exception{
        readUsersInfoFile infoFile = new readUsersInfoFile("output.csv", "input.txt");
        infoFile.addHeader();
        infoFile.readAndWriteToFile();

    }
}
