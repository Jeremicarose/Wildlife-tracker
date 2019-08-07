import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.assertEquals;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        Sql2o sql2otest = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "1234");
    }

    @Override
    protected void after() {
        try (Connection con =  DB.sql2otest.open()) {
            String deleteAnimalQuery = "DELETE FROM animal *;";
            String deleteSightingQuery = "DELETE FROM sighting *;";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
        }
    }
}
