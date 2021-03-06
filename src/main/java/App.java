import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());


        post("/view", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animalName");
            String animalAge = request.queryParams("animalAge");
            String animalHealth = request.queryParams("animalHealth");
            String rangerName = request.queryParams("rangerName");
            String sightingLocation = request.queryParams("sightingLocation");
            String animalType = request.queryParams("animalType");
            if(animalType.equals("safe")){
                Animal regularAnimal = new Animal(animalName, animalAge, animalHealth, animalType);
                regularAnimal.save();
                Sightings newSighting1 = new Sightings(rangerName, sightingLocation, regularAnimal.getId());
                newSighting1.save();
            } else if(animalType.equals("endangered")){
                Endangered endangeredAnimal = new Endangered(animalName, animalAge, animalHealth, animalType);
                endangeredAnimal.save();
                Sightings newSighting2 = new Sightings(rangerName, sightingLocation, endangeredAnimal.getId());
                newSighting2.save();
            }
            List<Sightings> allSightings = Sightings.all();
            List<Animal> allAnimal= Animal.all();
            model.put("sightings", allSightings);
            model.put("animals", allAnimal);
            return new ModelAndView(model, "view.hbs");
        }, new HandlebarsTemplateEngine());

        get("/view", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Sightings.all());
            model.put("animals", Animal.all());
            return new ModelAndView(model, "view.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
