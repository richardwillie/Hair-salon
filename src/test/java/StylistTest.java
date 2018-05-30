import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.time.LocalDateTime;

public class StylistTest {

   @Before
   public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "yomz", "@dZumi0991");
   }

   @After
   public void tearDown() {
      try(Connection con = DB.sql2o.open()) {
         String deleteTasksQuery = "DELETE FROM clients *;";
         String deleteCategoriesQuery = "DELETE FROM stylists *;";
         con.createQuery(deleteTasksQuery).executeUpdate();
         con.createQuery(deleteCategoriesQuery).executeUpdate();
      }
   }

   @Test
   public void save_savesIntoDatabase_true() {
      Stylist myStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
   }

   @Test
   public void find_returnsCategoryWithSameId_secondCategory() {
      Stylist firstStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Peter", "Savali", 29, "0701025463", "Hair Cutting");
      secondStylist.save();
      assertEquals(Stylist.find(secondStylist.getStylistId()), secondStylist);
   }

   @Test
   public void all_returnsAllInstancesOfStylist_true() {
      Stylist firstStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Peter", "Savali", 29, "0701025463", "Hair Cutting");
      secondStylist.save();
      assertEquals(true, Stylist.all().get(0).equals(firstStylist));
      assertEquals(true, Stylist.all().get(1).equals(secondStylist));
   }

   @Test
   public void save_assignsIdToObject() {
      Stylist myStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      myStylist.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(myStylist.getStylistId(), savedStylist.getStylistId());
   }

   @Test
   public void getClients_retrievesALlClientssFromDatabase_clientsList() {
      Stylist myStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      myStylist.save();
      Client firstClient = new Client("Cynthia", "Miguna", "0722510036", myStylist.getStylistId());
      firstClient.save();
      Client secondClient = new Client("Hildah", "Mulwa", "0770256984", myStylist.getStylistId());
      secondClient.save();
      Client[] clients = new Client[] { firstClient, secondClient };
      assertTrue(myStylist.getStylistClients().containsAll(Arrays.asList(clients)));
   }

   @Test
   public void getStylistId_stylistInstantiateWithAnId_1() {
      Stylist myStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      myStylist.save();
      assertTrue(myStylist.getStylistId() > 0);
   }

   @Test
   public void delete_deletesStylist() {
      Stylist myStylist = new Stylist("Susan", "Mutheu", 23, "0721560004", "Hair Coloring");
      myStylist.save();
      myStylist.find(myStylist.getStylistId());
      myStylist.delete();
      assertEquals(false, Stylist.all().contains(myStylist));
   }

      
}
