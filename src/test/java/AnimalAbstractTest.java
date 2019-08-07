import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalAbstractTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_InstantiatesCorrectly_true(){
        Animal myAnimal;
        myAnimal = new Animal("","","","");
        assertEquals(true, myAnimal instanceof Animal);
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithName_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("", myAnimal.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithAge_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("", myAnimal.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithHealth_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("", myAnimal.getHealth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithType_panther(){
        Animal myAnimal = new Animal("","","","");
        assertEquals("", myAnimal.getType());
    }


    // test to see if animal instantiates with an age and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithAge_mature(){
        Animal myAnimal = new Animal("","","","");
    }

    // test to see if animal instantiates with the health status and if an error is thrown
    @Test (expected = IllegalArgumentException.class)
    public void animal_InstantiatesWithHealth_Weak(){
        Animal myAnimal = new Animal("","","","");
    }
    @Test
    public void animal_IsSavedInDatabase(){
        Animal myAnimal = new Animal("", "", "","");
        myAnimal.save();
        assertTrue(Animal.all().get(0).equals(myAnimal));
    }
    //Test to check if all instances of regular animals are made
    @Test
    public void animal_AllInstancesOfAnimalAreReturned_True(){
        Animal myAnimal1 = new Animal("", "young","healthy","safe");
        myAnimal1.save();
        Animal myAnimal2 = new Animal("puma","mature","healthy","safe");
        myAnimal2.save();
        assertTrue(Animal.all().get(0).equals(myAnimal1));
        assertTrue(Animal.all().get(1).equals(myAnimal2));
    }
    //Animal is assigined an Id
    @Test
    public void animal_AnimalIsAssingnedAnID_getid(){
        Animal myAnimal = new Animal("puma","young","healthy","safe");
        myAnimal.save();
        Animal testAnimalia = Animal.all().get(0);
        assertEquals(myAnimal.getId(), testAnimalia.getId());
    }

    //Test to find Animal with the Same Id
    public void find_WillReturnAnimalWithTheSame_SecondAnimal(){
        Animal firstAnimal = new Animal("panther","mature","healthy","safe");
        firstAnimal.save();
        Animal secondAnimal = new Animal("puma","young","healthy","safe");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }


}