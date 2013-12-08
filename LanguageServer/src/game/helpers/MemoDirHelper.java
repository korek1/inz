package game.helpers;

import java.io.File;
import java.io.InputStream;

import utils.FileUtils;

public class MemoDirHelper {

    private static final String BASE_DIR = System.getProperty("user.dir") + File.separator + "teacher_files" + File.separator;
    private static final String MEMO_DIR = "memo" + File.separator;

    public static String saveMemoPic(String login, int gameID, InputStream pic, String extension)
    {
        String location = getMemoPath(Integer.toString(gameID), login);

        File f = new File(location);
        f.mkdirs();

        String uniqeFileName = generateUniqeFileName(location, "." + extension);

        String locationWithUniqeName = location + uniqeFileName;

        FileUtils.saveToFile(pic, locationWithUniqeName);

        return locationWithUniqeName;
    }

    private static String getMemoPath(String gameID, String login)
    {
        String memoLocation = BASE_DIR + login + File.separator + MEMO_DIR + gameID + File.separator;

        return memoLocation;
    }

    private static String generateUniqeFileName(String location, String extension)
    {
        int fileName = 0;

        File f = null;
        boolean fileExist = true;
        while (fileExist)
        {
            fileName++;
            f = new File(location + Integer.toString(fileName) + extension);
            fileExist = f.exists();
        }

        return Integer.toString(fileName) + extension;
    }

}
