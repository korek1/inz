package auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import utils.CommonUtils;

public class EncryptHelper {

    public static String createEncryptedPass(String pass)
    {
        String hashedPass = hashString(pass);

        return hashedPass;
    }

    public static boolean isPassCorrect(String userPass, String storedEncryptedPass)
    {
        boolean isCorrect = false;

        if (CommonUtils.isNotEmpty(userPass))
        {
            String hashedPass = hashString(userPass);

            if (hashedPass.equals(storedEncryptedPass))
            {
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    private static String hashString(String stringToHash)
    {
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(stringToHash.getBytes());
            byte byteData[] = digest.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
            {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
