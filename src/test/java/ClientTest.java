import java.time.LocalDateTime;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

   @Before
   public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "yomz", "@dZumi0991");
   }

   @After
   public void tearDown() {
      try(Connection con = DB.sql2o.open()) {
         String deleteClientsQuery = "DELETE FROM clients *;";
         String deleteStylistsQuery = "DELETE FROM stylists *;";
         con.createQuery(deleteClientsQuery).executeUpdate();
         con.createQuery(deleteStylistsQuery).executeUpdate();
      }
   }

   @Test
   public void equals_returnsTrueIfClientsAretheSame() {
      Client firstClient = new Client("Susan", "Mutheu", "0711400512", 1);
      Client secondClient = new Client("Susan", "Mutheu", "0711400512", 1);
      assertTrue(firstClient.equals(secondClient));
   }

   @Test
   public void save_savesIntoDatabase_true() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      myClient.save();
      assertTrue(Client.all().get(0).equals(myClient));
   }

   @Test
   public void getClientStylistId_returnsTheClientsStylistId() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      myClient.save();
      int stylistId = myClient.getClientStylistId();
      assertEquals(1, stylistId);
   }

   @Test
   public void all_returnsAllInstancesOfClient_true() {
      Client firstClient = new Client("Susan", "Mutheu", "0711400512", 1);
      firstClient.save();
      Client secondClient = new Client("Peter", "Savali", "0711568974", 1);
      secondClient.save();
      assertEquals(true, Client.all().get(0).equals(firstClient));
      assertEquals(true, Client.all().get(1).equals(secondClient));
   }

   @Test
   public void getId_clientsInstantiateWithAnID() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      myClient.save();
      assertTrue(myClient.getClientId() > 0);
   }

   @Test
   public void find_returnsClientWithSameId_secondClient() {
      Client firstClient = new Client("Susan", "Mutheu", "0711400512", 1);
      firstClient.save();
      Client secondClient = new Client("Peter", "Savali", "0711568974", 1);
      secondClient.save();
      assertEquals(Client.find(secondClient.getClientId()), secondClient);
   }

   @Test
   public void update_updatesClientDetails_true() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      myClient.save();
      assertTrue(Client.all().get(0).equals(myClient));
   }

   @Test
   public void getClientCreatedAt_returnsTheClientCreatedAt() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      LocalDateTime createdAt = myClient.getClientCreatedAt();
      assertEquals(createdAt, myClient.getClientCreatedAt());
   }

   @Test
   public void delete_deletesClientFromStylist() {
      Client myClient = new Client("Susan", "Mutheu", "0711400512", 1);
      myClient.save();
      myClient.find(myClient.getClientId());
      myClient.delete();
      assertEquals(false, Client.all().contains(myClient));
   }
      
}
