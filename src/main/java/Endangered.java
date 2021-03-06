import org.sql2o.Connection;

import java.util.List;

public class Endangered extends  AnimalAbstract {
    private static final String ANIMAL_TYPE = "endangered";
    public Endangered(String name, String age, String health, String type) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.type = ANIMAL_TYPE;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if(!(otherAnimal instanceof Object)){
            return false;
        }
        AnimalAbstract myAnimal = (AnimalAbstract) otherAnimal;
        return this.getName().equals(myAnimal.getName())&&
                this.getType().equals(myAnimal.getType())&&
                this.getId()==myAnimal.getId() ;

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
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Endangered> all(){
        String sql = "SELECT * FROM animals WHERE type='endangered'";
        try(org.sql2o.Connection con = DB.sql2otest.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }

}
