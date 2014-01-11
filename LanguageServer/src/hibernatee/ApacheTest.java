package hibernatee;

import game.to.GameTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import auth.Auth;

import com.google.gson.Gson;

import dto.to.KlasaTO;
import dto.to.KlasaTOs;

public class ApacheTest {

    static HttpClient client = new DefaultHttpClient();
    static Gson g = new Gson();

    public static void main(String[] args) throws Exception
    {

        String tempPass = login("jan", "pass", true);

        // checkKlasy(tempPass);

        // tempPass = login("Kot1a113", "pass", false);

        // getMemos(tempPass); // pamietaj ze trzeba najpierw dodaæ gre memo
        // bedac nauczycielem i w lini 48 podac jej ID - ja akurat mialem id=11
        // zdjecia dodawaj png bo tak tu jest ustawione na sztywno

        postMemo(tempPass);

    }

    private static void postMemo(String tempPass) throws URISyntaxException, HttpException, IOException
    {
        HttpPost post = new HttpPost("http://localhost:8080/LanguageServer/game/memo");

        post.addHeader("login", "jan");
        post.addHeader("pass", tempPass);
        post.addHeader("Accept", "*/*");

        GameTO gameTO = new GameTO();
        gameTO.setName("gamename2");
        gameTO.setDifficultyFactor(4);
        gameTO.setCategoryId(4);
        String jsonGameTO = g.toJson(gameTO);

        MultipartEntity multipart = new MultipartEntity();

        StringBody jsonContent = new StringBody(jsonGameTO);
        multipart.addPart("gamedetails", jsonContent);

        File imageCat = new File("C:\\Users\\acer\\Desktop\\memo\\cat.PNG");
        multipart.addPart("cat", new FileBody(imageCat));

        File imageDog = new File("C:\\Users\\acer\\Desktop\\memo\\dog.PNG");
        multipart.addPart("dog", new FileBody(imageDog));

        File imageBug = new File("C:\\Users\\acer\\Desktop\\memo\\bug.PNG");
        multipart.addPart("bug", new FileBody(imageBug));

        post.setEntity(multipart);

        HttpResponse execute = client.execute(post);

        StatusLine statusLine = execute.getStatusLine();

        System.out.println(statusLine.getStatusCode());
    }

    private static void getMemos(String tempPass) throws Exception
    {
        HttpGet get = new HttpGet("http://localhost:8080/LanguageServer/game/student/memo/11"); // tu musisz podac id gry ktora dodasz

        get.addHeader("login", "Kot1a113");
        get.addHeader("pass", tempPass);
        get.addHeader("Accept", "*/*");

        HttpResponse response = client.execute(get);

        Header[] headers = response.getHeaders("Content-Type");
        String value = headers[0].getValue();

        int indexOf = value.indexOf("boundary=");
        String boundary = value.substring(indexOf + 9); // wyciagamy boundary z
                                                        // headera

        byte[] bytes = boundary.getBytes();

        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();

        @SuppressWarnings("deprecation")
        MultipartStream multipartStream = new MultipartStream(content, bytes);

        multipartStream.skipPreamble();
        boolean bool = true;
        int i = 1;
        while (bool) // lecimy po wszytskich czesciach miltipart data form -
                     // czyli jak dodasz 4 obrazki to przeleci petle 4 razy
        {
            String readHeaders = multipartStream.readHeaders();
            System.out.println(readHeaders);

            String location = "C:\\Users\\acer\\Desktop\\fromserv\\"; // tu sobie zmien na swoja lokalna lokalizacje

            File f = new File(location + i++ + ".png");
            OutputStream output = new FileOutputStream(f);
            multipartStream.readBodyData(output);

            bool = multipartStream.readBoundary();
            output.close();
        }
        // po wykonaniu tego powinnas miec zapisane wszytskie image z serwera w
        // folderze jaki podalas wyzej
        // a naglowiki na consoli wypisane
        // np Content-Type: image/PNG w tym wypadku bo z serwera leci png
        // jak Kukuis przesle mi jpg to bedzie image/jpg wiec na to trzeba
        // zwrocic uwagê

    }

    private static String login(String login, String pass, boolean teacher) throws Exception
    {

        HttpPost post = null;
        if (teacher)
        {
            post = new HttpPost("http://localhost:8080/LanguageServer/login/teacher");
        }
        else
        {
            post = new HttpPost("http://localhost:8080/LanguageServer/login/student");
        }

        post.addHeader("Content-Type", "application/json");

        Auth auth = new Auth();
        auth.setLogin(login);
        auth.setPass(pass);
        String json2 = g.toJson(auth);

        StringEntity json = new StringEntity(json2);
        post.setEntity(json);

        HttpResponse response = client.execute(post);

        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        System.out.println(statusCode);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String tempPass = "";
        while ((line = rd.readLine()) != null)
        {
            tempPass += line;
        }

        System.out.println(tempPass);
        return tempPass;
    }

    private static void checkKlasy(String tempPass) throws Exception
    {
        HttpGet get = new HttpGet("http://localhost:8080/LanguageServer/classes");

        get.addHeader("login", "jan");
        get.addHeader("pass", tempPass);
        get.addHeader("Accept", "*/*");

        HttpResponse response = client.execute(get);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        String jsonKlasy = "";
        while ((line = rd.readLine()) != null)
        {
            jsonKlasy += line;
        }

        KlasaTOs klasy = g.fromJson(jsonKlasy, KlasaTOs.class);
        for (KlasaTO klasa : klasy.getKlases())
        {
            System.out.println("klasa name : " + klasa.getName());
            System.out.println("klasa id : " + klasa.getId());
            System.out.println();
        }
    }

}
