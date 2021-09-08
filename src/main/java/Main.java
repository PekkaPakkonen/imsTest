public class Main {
    public static void main(String[] args) throws Exception{
        readUsersInfoFile infoFile = new readUsersInfoFile("kek.csv", "text.txt");
        infoFile.addHeader();
        infoFile.readAndWriteToFile();

    }
}
