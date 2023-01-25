package it.polimi.gabrielegiusti.classes.utils;

public class ModelUtils {

    public static String retrieveCollectionNameFromModel(Object className){

        String classname = className.getClass().toString().split(" ")[1];

        if (classname.contains("Model")){
            classname = classname.replaceAll("Model", "");
        }

        return classname;
    }
}
