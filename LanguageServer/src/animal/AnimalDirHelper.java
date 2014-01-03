package animal;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import utils.CommonUtils;
import utils.FileUtils;

public class AnimalDirHelper {

    private static final String BASE_DIR = System.getProperty("user.dir") + File.separator + "student_files" + File.separator;
    private static final String ANIMAL_DIR = "animal" + File.separator;

    private final static String fileName1 = "file";
    private final static String fileName2 = "eq";
    private final static String fileName3 = "info";
    private final static String fileName4 = "img";

    public static void storeAnimal(String login, InputStream... files)
    {
        String path = BASE_DIR + login + File.separator + ANIMAL_DIR;

        File f = new File(path);
        f.mkdirs();

        List<String> list = getList(path);

        int i = 0;
        for (InputStream inputStream : files)
        {
            if(CommonUtils.isNotNull(inputStream))
            {
                FileUtils.saveToFile(inputStream, list.get(i));
            }
            i++;
        }
    }

    public static List<File> getAnimalFile(String login)
    {
        String path = BASE_DIR + login + File.separator + ANIMAL_DIR;

        File f1 = new File(path + fileName1);
        File f2 = new File(path + fileName2);
        File f3 = new File(path + fileName3);
        File f4 = new File(path + fileName4);

        List<File> listFiles = new ArrayList<>();
        listFiles.add(f1);
        listFiles.add(f2);
        listFiles.add(f3);
        listFiles.add(f4);

        return listFiles;
    }

    private static List<String> getList(String path)
    {
        List<String> list = new ArrayList<>();
        String location1 = path + fileName1;
        String location2 = path + fileName2;
        String location3 = path + fileName3;
        String location4 = path + fileName4;

        list.add(location1);
        list.add(location2);
        list.add(location3);
        list.add(location4);

        return list;
    }

}
