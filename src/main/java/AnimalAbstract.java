import org.sql2o.Connection;

public class AnimalAbstract {
    public int id;
    public String name;
    public String age;
    public String health;
    public String type;

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }
    public String getHealth() {
        return health;
    }
    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }

    public void save() {
        try (org.sql2o.Connection con = DB.sql2otest.open()) {
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age, :health, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static AnimalAbstract find(int id) {
        String sql = "SELECT * FROM animals WHERE id = :id;";
        try (Connection con = DB.sql2otest.open()) {
            AnimalAbstract myAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(AnimalAbstract.class);
            return myAnimal;

        }


    }
}
