import org.sql2o.Sql2o;

public class DB {

    public static Sql2o sql2otest = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "1234");
}
