package animal;

import java.io.File;
import java.io.InputStream;

import utils.FileUtils;

public class AnimalDirHelper {

    private static final String BASE_DIR = System.getProperty("user.dir") + File.separator + "student_files" + File.separator;
    private static final String ANIMAL_DIR = "animal" + File.separator;

    public static void storeAnimal(String login, InputStream file)
    {
        String location = BASE_DIR + login + File.separator + ANIMAL_DIR + "file";

        File f = new File(location);
        f.mkdirs();

        FileUtils.saveToFile(file, location);
    }

    public static File getAnimalFile(String login)
    {
        File f = new File(BASE_DIR + login + File.separator + ANIMAL_DIR + "file");

        return f;
    }

}
