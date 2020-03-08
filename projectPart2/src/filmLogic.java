import java.util.Scanner;

public class filmLogic {

    String test = "select rolleNavn from project1moveis.skuespiller " +
            "inner join skuespiller on person.personID = skuespiller.skuespillerID" +
            "where navn = 'donald'";

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String userName;

        // Enter username and press Enter
        System.out.println("Enter username");
        userName = myObj.nextLine();

        System.out.println("Username is: " + userName);
    }
}
