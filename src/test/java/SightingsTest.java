import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Sighting_InstantiatesCorrectly_True(){
        Sightings newsighting = new Sightings("","",1);
        assertTrue( newsighting instanceof Sightings);
    }
    @Test
    public void Sighting_IsSavedOnDataBase_True(){
        Sightings newSighting = new Sightings("","",1);
        newSighting.save();
        assertTrue(Sightings.all().get(0).equals(newSighting));
    }
    @Test
    public void sighting_EachSigthingIsAssignedAnId_getid(){
        Sightings newSighting = new Sightings("","",1);
        newSighting.save();
        Sightings testSighting = Sightings.all().get(0);
        assertEquals(newSighting.getId(), testSighting.getId());
    }
    @Test
    public void find_WillReturnSightingWithTheSameID_SecondSighting(){
        Sightings firstSighting = new Sightings("","",1);
        firstSighting.save();
        Sightings SecondSighting = new Sightings("","",3);
        SecondSighting.save();
        assertEquals(SecondSighting, Sightings.find(SecondSighting.getId()));
    }
//    @Test
//    public void Sightings_CanBeDeletedFromDataBase_true() {
//        Sightings newSighting = new Sightings("","",1);
//        newSighting.save();
//        newSighting.delete(newSighting.getAnimalId());
//        assertEquals(0, Sightings.all().size());
//    }



}