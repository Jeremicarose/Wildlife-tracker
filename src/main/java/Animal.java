import org.sql2o.Connection;

import java.util.List;

public class Animal extends AnimalAbstract  {
    private static final String ANIMAL_TYPE = "safe";
    public Animal(String name, String age, String health, String type){
        this.name = name;
        this.age = age;
        this.health = health;
        this.type = ANIMAL_TYPE;

    }
    @Override
    public void save(){
        try(Connection con = DB.sql2otest.open()){
            String sql = "INSERT INTO animals (name, age, health, type) VALUES (:name, :age, :health, :type);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
    @Override
    public boolean equals(Object otherAnimal){
        AnimalAbstract myAnimal = (AnimalAbstract) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getType().equals(myAnimal.getType())&&
                this.getId()==myAnimal.getId() ;

    }
    public static List<Animal> all(){
        String sql = "SELECT * FROM animals WHERE type='safe'";
        try(Connection con = DB.sql2otest.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

}
