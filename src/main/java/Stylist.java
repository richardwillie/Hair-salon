
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
   private String name;
   private String phone;
   private String department;

   private int id;

   public Stylist(String name, String phone, String dept) {
      this.name = name;
      this.phone = phone;
      this.department = dept;
   }

   public String getstylistname() {
      return name;
   }

   public String getstylistphone() {
      return phone;
   }

   public String getdepartment() {
      return department;
   }

   public int getId() {
      return id;
   }

   public static List<Stylist> all() {
      String sql = "SELECT id, name, phone, department FROM stylists";
         try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Stylist.class);
      }
   }

   public List<Client> getclients() {
      try(Connection con = DB.sql2o.open()) {
         String sql = "SELECT * FROM clients where stylistid=:id";
         return con.createQuery(sql)
         .addParameter("id", this.id)
         .executeAndFetch(Client.class);
      }
   }

   public static Stylist find(int id) {
      try(Connection con = DB.sql2o.open()) {
         String sql = "SELECT * FROM stylists where id=:id";
         Stylist stylist = con.createQuery(sql)
         .addParameter("id", id)
         .executeAndFetchFirst(Stylist.class);
         return stylist;
      }
   }

   public void save() {
      try(Connection con = DB.sql2o.open()) {
         String sql = "INSERT INTO stylists(name, phone, department) VALUES (:name, :phone, :department)";
         this.id = (int) con.createQuery(sql, true)
         .addParameter("name", this.name)
         .addParameter("phone", this.phone)
         .addParameter("department", this.department)
         .executeUpdate()
         .getKey();
      }
   }

   // public void update(String first_name, String last_name, int age, String phone_no, String department) {
   //    try(Connection con = DB.sql2o.open()) {
   //       String sql = "UPDATE stylists SET first_name = :first_name, last_name = :last_name, age = :age, phone_no = :phone_no, department = :department  WHERE id = :id";
   //       con.createQuery(sql)
   //       .addParameter("first_name", first_name)
   //       .addParameter("last_name", last_name)
   //       .addParameter("age", age)
   //       .addParameter("phone_no", phone_no)
   //       .addParameter("department", department)
   //       .addParameter("id", id)
   //       .executeUpdate();
   //    }
   // }

   public void delete() {
      try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id;";
      con.createQuery(sql)
         .addParameter("id", id)
         .executeUpdate();
      }
   }
}
