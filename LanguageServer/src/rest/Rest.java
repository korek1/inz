package rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import rest.auth.Role;
import spring.BeanHelper;
import spring.klasa.KlasaManager;
import spring.student.StudentManager;
import spring.teacher.TeacherManager;
import auth.AuthMenager;
import dto.Klasa;
import dto.Student;
import dto.Teacher;
import dto.to.IsOnlineTO;
import dto.to.KlasaTO;
import dto.to.KlasaTOs;
import dto.to.OnlineTOs;
import dto.to.StudentTO;
import dto.to.TOsManager;
import dto.to.TeacherTO;
import dto.to.toserver.KlasaInsertTO;
import dto.to.toserver.StudentInsertTO;
import dto.to.toserver.TeacherInsertTO;

@Path("/")
public class Rest {

    @Context
    SecurityContext context;

    StudentManager studentManager = (StudentManager) BeanHelper.getBean("studentManagerImpl");
    KlasaManager klasaManager = (KlasaManager) BeanHelper.getBean("klasaManagerImpl");
    TeacherManager teacherManager = (TeacherManager) BeanHelper.getBean("teacherManagerImpl");

    public Rest()
    {
        super();
    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentTO getStudent(@PathParam("id") int id)
    {

        Student student = studentManager.getStudentById(id);
        StudentTO studentTO = TOsManager.convertStudent(student);

        return studentTO;

    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/teacher/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeacherTO getTeacher(@PathParam("id") int id)
    {
        Teacher teacher = teacherManager.getTeacherById(id);
        TeacherTO teacherTO = TOsManager.convertTeacher(teacher);

        return teacherTO;
    }

    @GET
    @RolesAllowed({ Role.STUDENT, Role.TEACHER })
    @Path("/class/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public KlasaTO getKlase(@PathParam("id") int id)
    {
        Klasa klasa = klasaManager.getKlasaById(id);
        KlasaTO klasaTO = TOsManager.convertKlasa(klasa);

        return klasaTO;
    }

    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/classes")
    @Produces(MediaType.APPLICATION_JSON)
    public KlasaTOs getAllKlase(@HeaderParam("login") String login)
    {
        List<Klasa> allClasses = klasaManager.getAllKlasy(login);
        KlasaTOs klasaTOs = TOsManager.convertKlases(allClasses);

        return klasaTOs;
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/class")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Integer addClass(KlasaInsertTO klasa, @HeaderParam("login") String login)
    {

        Integer id = klasaManager.insertKlasa(new Klasa(klasa), login);

        return id;
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/teacher")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Integer postTeacher(TeacherInsertTO teacher)
    {
        Integer id = teacherManager.insertTeacher(new Teacher(teacher));

        return id;
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/student")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Integer postStudent(StudentInsertTO student, @HeaderParam("login") String login)
    {

        Integer id = studentManager.insertStudent(student, login);

        return id;
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/student/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateStudent(StudentInsertTO student, @PathParam("id") int id)
    {

        studentManager.updateStudent(student, id);

        return "succes";
    }
    
    @POST
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/pass")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String changePass(String newPass, @HeaderParam("login") String login)
    {

        studentManager.changePass(newPass, login);

        return "succes";
    }

    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/online")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OnlineTOs getOnline(IsOnlineTO isOnlineTO)
    {
        OnlineTOs onlineTOs = AuthMenager.checkWhoIsOnline(isOnlineTO);

        return onlineTOs;
    }

    // /**
    // * Image
    // */
    // @POST
    // @Path("/image")
    // @Consumes(MediaType.MULTIPART_FORM_DATA)
    // @Produces(MediaType.TEXT_PLAIN)
    // public String uploadFile(@FormDataParam("file") InputStream
    // uploadedInputStream, @FormDataParam("file") FormDataContentDisposition
    // fileDetail)
    // {
    // // need be customized
    // String uploadedFileLocation = "C:\\Users\\acer\\Desktop\\res\\" +
    // fileDetail.getFileName();
    //
    // // save it
    // FileUtils.saveToFile(uploadedInputStream, uploadedFileLocation);
    //
    // return "hehs";
    //
    // }
    //
    // @GET
    // @Path("/image")
    // @Produces("image/png")
    // public Response get()
    // {
    // File file = new File("C:\\Users\\acer\\Desktop\\postStudent.png");
    //
    // ResponseBuilder response = Response.ok((Object) file);
    // response.header("Content-Disposition",
    // "attachment; filename=image_from_server.png");
    // return response.build();
    // }
    //
    // // ////////////////////////////////

}