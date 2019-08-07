import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.assertEquals;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        Sql2o sql2otest = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "1234");
//        Sql2o sql2otest =new Sql2o("jdbc:postgresql://ec2-107-20-168-237.compute-1.amazonaws.com:5432/d9k1tqha0llkk4", "yrxrlurosbfpbz", "281e6d0a171d5c660aa7580223f0aa60ff48d39e70ee83f4ffa8b68d8748a081");
    }

    @Override
    protected void after() {
        try (Connection con =  DB.sql2otest.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteSightingQuery = "DELETE FROM sightings *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
        }
    }
}
