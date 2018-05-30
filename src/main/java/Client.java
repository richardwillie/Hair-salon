import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Client {
   private String name;
   private String phone;
   private int stylistid;

   private int id;

   public Client(String clientname, String clientphone, int stylistId) {
      this.name = clientname;
      this.phone = clientphone;
      this.stylistid = stylistId;
   }

   public String getclientname() {
      return name;
   }

   public String getclientphone() {
      return phone;
   }

   public int getclientstylistId() {
      return stylistid;
   }

   public int getId() {
      return id;
   }

   public static List<Client> all() {
      String sql = "SELECT id, name, phone, client_phone_no, stylistid FROM clients";
         try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Client.class);
      }
   }

   public static Client find(int id) {
      try(Connection con = DB.sql2o.open()) {
         String sql = "SELECT * FROM clients where id=:id";
         Client client = con.createQuery(sql)
         .addParameter("id", id)
         .executeAndFetchFirst(Client.class);
         return client;
      }
   }

   public void save() {
      try(Connection con = DB.sql2o.open()) {
         String sql = "INSERT INTO clients(name, phone, stylistid) VALUES (:name, :phone, :stylistid)";
         this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .addParameter("phone", this.phone)
         .addParameter("stylistid", this.stylistid)
         .executeUpdate()
         .getKey();
      }
   }

   public void delete() {
      try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
         .addParameter("id", id)
         .executeUpdate();
      }
   }

  
}
