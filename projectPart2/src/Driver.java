import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    // Task 1
    public void getRollenavn(String skuespillernavn){
        try{
            // 1. get a connection to DB
            Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/project1moveis?useSSL=false", "root", "igothacked1");

            // 2. create a statement
            Statement myStmt = myConn.createStatement();

            // 3. execute sql query
            //ResultSet myRs = myStmt.executeQuery("SELECT * FROM project1moveis.person");
            ResultSet myRs = myStmt.executeQuery("select skuespiller.rolleNavn from skuespiller " +
                    "inner join person on skuespiller.personID = person.personID " +
                    "where person.navn = " + "\'" + skuespillernavn + "\'");

            // 4. process the result set
            while (myRs.next()){
                System.out.println(myRs.getString(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Task 2
    public void getMovies(String skuespillerNavn){
        try{
            // 1. get a connection to DB
            Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/project1moveis?useSSL=false", "root", "igothacked1");

            // 2. create a statement
            Statement myStmt = myConn.createStatement();

            // 3. execute sql query
            //ResultSet myRs = myStmt.executeQuery("SELECT * FROM project1moveis.person");
            ResultSet myRs = myStmt.executeQuery("select medier.Tittel from medier "+
                    "inner join skuespiller on medier.MedieID = skuespiller.medieID "+
                    "inner join person on skuespiller.personID = person.personID "+
                    "where person.navn = " + "\'" + skuespillerNavn + "\'");

            // 4. process the result set
            while (myRs.next()){
                System.out.println(myRs.getString(1));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Task 3
    public void getCompany(){
        try{
            // 1. get a connection to DB
            Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/project1moveis?useSSL=false", "root", "igothacked1");

            // 2. create a statement
            Statement myStmt = myConn.createStatement();

            // 3. execute sql query
            //ResultSet myRs = myStmt.executeQuery("SELECT * FROM project1moveis.person");
            ResultSet myRs = myStmt.executeQuery("select selskap.navn, kategori.navn, count(selskap.selskapID) from selskap\n" +
                    "inner join utgiver on selskap.selskapID = utgiver.selskapID\n" +
                    "inner join medier on utgiver.MedieID = medier.MedieID\n" +
                    "inner join sjangerimedie on medier.MedieID = sjangerimedie.MedieID\n" +
                    "inner join kategori on sjangerimedie.kategoriID = kategori.kategoriID\n" +
                    "group by kategori.kategoriID, selskap.selskapID");

            // 4. process the result set
            while (myRs.next()){
                System.out.println(myRs.getString("navn") +"    "+
                        myRs.getString("kategori.navn")+"    "+
                        myRs.getString("count(selskap.selskapID)"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Task 4
    // newMovie = "insert into medier values(7,"Vulkanveien","Film",85,2020,"2019-01-01","Donald på nye eventry","kino",NULL);
    //newSkuespiller = insert into skuespiller values(4,7,"Sauron")
    //newRegissør = insert into regissør values(8,7)
    //newManusForfatter = insert into manusforfatter values(6,7)
    //newSjangerIMedie = insert into sjangerimedie values(3,7)
    //newUtgiver = insert into utgiver values(1,7)
    public void insertMovie(){

        try{
            // 1. get a connection to DB
            Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/project1moveis?useSSL=false", "root", "igothacked1");

            // 2. create a statement
            Statement myStmt = myConn.createStatement();

            // 3. execute sql query
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter an ID");
            Integer id = Integer.parseInt(myObj.nextLine());
            System.out.println(id);

            Scanner myObj2 = new Scanner(System.in);
            System.out.println("Enter a Title");
            String title = myObj2.nextLine();
            System.out.println(title);

            Scanner myObj3 = new Scanner(System.in);
            System.out.println("Enter a media type");
            String medType = myObj3.nextLine();
            System.out.println(medType);

            Scanner myObj4 = new Scanner(System.in);
            System.out.println("Enter length");
            Integer lengdes = Integer.parseInt(myObj4.nextLine());
            System.out.println(lengdes);

            Scanner myObj5 = new Scanner(System.in);
            System.out.println("Enter a date");
            Integer dates = Integer.parseInt(myObj5.nextLine());
            System.out.println(dates);

            Scanner myObj6 = new Scanner(System.in);
            System.out.println("Enter lanseringsdato");
            String lanseringsdato = myObj6.nextLine();
            System.out.println(lanseringsdato);

            Scanner myObj7 = new Scanner(System.in);
            System.out.println("Enter a storyline");
            String story = myObj7.nextLine();
            System.out.println(story);

            Scanner myObj8 = new Scanner(System.in);
            System.out.println("Enter made for");
            String madefor = myObj8.nextLine();
            System.out.println(madefor);



            String newMovie = "insert into medier (MedieID, Tittel, MediaType,Lengde, Utgivelsesår, Lanseringsdato, Storyline, LagetFor) " +
                    "values (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = myConn.prepareStatement(newMovie);
            ps.setInt(1,id);
            ps.setString(2, title);
            ps.setString(3, medType);
            ps.setInt(4, lengdes);
            ps.setInt(5, dates);
            ps.setString(6, lanseringsdato);
            ps.setString(7, story);
            ps.setString(8, madefor);
            ps.executeUpdate();

            // 4. process the result set
            System.out.println("New movie created");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //ArrayList inputs = new ArrayList([8,'tittel', 'mediatype', 123, 2020, '2020-09-01', 'hei jeg er en film','kino']);
        Driver driver = new Driver();

        driver.getRollenavn("Dole");  //Task 1
        //driver.getMovies("Ole");  //Task 2
        //driver.getCompany();  //Task 3
        //driver.insertMovie();  //Task 4
    }
}
