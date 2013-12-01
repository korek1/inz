package rest;

import hibernatee.DBUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import spring.BeanHelper;
import spring.klasa.KlasaManager;
import spring.student.StudentManager;
import spring.teacher.TeacherManager;
import utils.FileUtils;
import dto.Klasa;
import dto.Student;
import dto.Teacher;

@Path("/")
public class Rest extends Application {

    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");
    KlasaManager klasaManager = (KlasaManager) BeanHelper.getBean("klasaManagerImpl");
    TeacherManager teacherManager = (TeacherManager) BeanHelper.getBean("teacherManagerImpl");

    /**
     * Default constructor.
     */
    public Rest()
    {
        super();
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MultiPartFeature.class);
        return classes;
    }

    @GET
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudent(@PathParam("id") int id)
    {

        Student student = studentManager.getStudentById(id);
        DBUtils.cleanStudent(student);

        return student;

    }

    @GET
    @Path("/teacher/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Teacher getTeacher(@PathParam("id") int id)
    {
        Teacher teacher = teacherManager.getTeacherById(id);
        DBUtils.cleanTeacher(teacher);

        return teacher;
    }

    @POST
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postTeacher(Teacher teacher)
    {
        teacherManager.insertTeacher(teacher);

        return "succes";
    }

    @GET
    @Path("/class/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Klasa getKlase(@PathParam("id") int id)
    {
        Klasa klasa = klasaManager.getKlasaById(id);
        DBUtils.cleanKlasa(klasa);

        return klasa;
    }

    @GET
    @Path("/classes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Klasa> getAllKlase(@HeaderParam("login") String login)
    {
        List<Klasa> allClasses = klasaManager.getAllKlasy(login);
        DBUtils.cleanKlasy(allClasses);

        return allClasses;
    }

    @POST
    @Path("/class")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addClass(Klasa klasa, @HeaderParam("login") String login)
    {

        klasaManager.insertKlasa(klasa, login);

        return "succes";
    }

    @POST
    @Path("/student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postStudent(Student student, @HeaderParam("login") String login, @HeaderParam("id") int idKlasy)
    {

        studentManager.insertStudent(student, login, idKlasy);

        return "succes";
    }

    /**
     * Image
     */
    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail)
    {
        // need be customized
        String uploadedFileLocation = "C:\\Users\\acer\\Desktop\\res\\" + fileDetail.getFileName();

        // save it
        FileUtils.saveToFile(uploadedInputStream, uploadedFileLocation);

        return "hehs";

    }

    @GET
    @Path("/image")
    @Produces("image/png")
    public Response get()
    {
        File file = new File("C:\\Users\\acer\\Desktop\\postStudent.png");

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=image_from_server.png");
        return response.build();
    }

}