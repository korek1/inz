package rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

    /* student */

    /**
     * Returns information about concrete student.
     * 
     * @param id - student ID
     * @return
     */
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

    /**
     * Returns information about student which executed method
     * 
     * @param login - student's login
     * @return
     */
    @GET
    @RolesAllowed({ Role.STUDENT })
    @Path("/student/me")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentTO getStudentByLogin(@HeaderParam("login") String login)
    {

        Student student = studentManager.getStudentByLogin(login);
        StudentTO studentTO = TOsManager.convertStudent(student);

        return studentTO;

    }

    /**
     * Insert new student to database.
     * 
     * @param student - student to insert
     * @param login - teacher's login
     * @return
     */
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

    /**
     * Updates info about student
     * 
     * @param student - info to update
     * @param id - ID of student to update
     * @return
     */
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

    @DELETE
    @RolesAllowed({ Role.TEACHER })
    @Path("/student/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteStudent(@PathParam("id") int id)
    {

        studentManager.deleteStudent(id);

        return "succes";
    }

    /**
     * Changes student's password
     * 
     * @param newPass - new password to remeber in DB
     * @param login - student's login
     * @return
     */
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

    /**
     * Returns info if selected students are online.
     * 
     * @param isOnlineTO - list of student
     * @return
     */
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

    /* teacher */

    /**
     * Returns info about concrete teacher.
     * 
     * @param id - Teacher's ID
     * @return
     */
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

    /**
     * Returns info about teacher which executed method.
     * 
     * @param login - teacher's login
     * @return
     */
    @GET
    @RolesAllowed({ Role.TEACHER })
    @Path("/teacher/me")
    @Produces(MediaType.APPLICATION_JSON)
    public TeacherTO getTeacherByLogin(@HeaderParam("login") String login)
    {
        Teacher teacher = teacherManager.getTeacherByLogin(login);
        TeacherTO teacherTO = TOsManager.convertTeacher(teacher);

        return teacherTO;
    }

    /**
     * Adds new teacher to DB
     * 
     * @param teacher - teacher to insert
     * @return
     */
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

    /**
     * Updates info about teacher which executed method
     * 
     * @param login - teacher's login
     * @param teacher - info to update
     * @return
     */
    @POST
    @RolesAllowed({ Role.TEACHER })
    @Path("/teacher/me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateTeacher(@HeaderParam("login") String login, TeacherInsertTO teacher)
    {
        teacherManager.updateTeacher(login, teacher);

        return "ok";
    }

    /* class */

    /**
     * Returns info about concrete class
     * 
     * @param id - ID of a class
     * @return
     */
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

    /**
     * Returns all classes which belongs to a teacher which executed method
     * 
     * @param login - teacher's login
     * @return
     */
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

    /**
     * Adds new class do DB
     * 
     * @param klasa
     * @param login
     * @return
     */
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

}