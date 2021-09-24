public class Main {
    public static void main(String[] args) throws Exception{
        readUsersInfoFile infoFile = new readUsersInfoFile("users.csv", "text.txt");
        infoFile.addHeader();
        infoFile.readAndWriteToFile();

    }
}
