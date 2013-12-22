package hibernatee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dto.Klasa;
import dto.Student;
import dto.Teacher;
import dto.games.RozsypankaGame;

public class HiberTest {

    static DBController db;

    public static void main(String[] args)
    {

        db = DBController.getInstance();
        Session session = db.getSession();

        Teacher teacher1 = new Teacher(null, null, "pass", "admin");
        Teacher teacher2 = new Teacher("Jan", "Kowalski", "pass", "jan");

        Klasa klasa = new Klasa();
        klasa.setName("1a");
        klasa.setYear(2013);

        Klasa klasa2 = new Klasa();
        klasa2.setName("1b");
        klasa2.setYear(2013);

        Student student = new Student();
        student.setFirstName("Adam");
        student.setLastName("Kot");
        student.setKlasa(klasa);
        student.setLogin("Kot1a113");
        student.setPassword("pass");
        student.setOrderNoumber(1);

        Student student2 = new Student();
        student2.setFirstName("Aga");
        student2.setLastName("Byk");
        student2.setKlasa(klasa2);
        student2.setLogin("Byk1a113");
        student2.setPassword("pass");
        student2.setOrderNoumber(1);

        Student student3 = new Student();
        student3.setFirstName("Aga");
        student3.setLastName("XYZ");
        student3.setKlasa(klasa);
        student3.setLogin("a");
        student3.setPassword("a");
        student3.setOrderNoumber(2);

        Student student4 = new Student();
        student4.setFirstName("Aga");
        student4.setLastName("XXX");
        student4.setKlasa(klasa);
        student4.setLogin("b");
        student4.setPassword("b");
        student4.setOrderNoumber(3);

        klasa.setTeacher(teacher2);
        klasa2.setTeacher(teacher2);

        RozsypankaGame rozsypankaGame = new RozsypankaGame();
        rozsypankaGame.setCategory(0);
        rozsypankaGame.setDifficultyFactor(5);
        rozsypankaGame.setOwner(teacher2);
        rozsypankaGame.setName("JakisInnyName");

        List<String> sentences = new ArrayList<>();
        sentences.add("Pierwsze fajne zdanie");
        sentences.add("Drugie fajne zdanie");
        sentences.add("Trzecie fajne zdanie");
        rozsypankaGame.setSentences(sentences);

        RozsypankaGame rozsypankaGame2 = new RozsypankaGame();
        rozsypankaGame2.setCategory(5);
        rozsypankaGame2.setDifficultyFactor(10);
        rozsypankaGame2.setOwner(teacher2);
        rozsypankaGame2.setName("JakisName");

        List<String> sentences2 = new ArrayList<>();
        sentences2.add("Pierwsze fajne zdanie");
        sentences2.add("Drugie fajne zdanie");
        sentences2.add("Trzecie fajne zdanie");
        rozsypankaGame2.setSentences(sentences2);

        try
        {
            Transaction beginTransaction = session.beginTransaction();

            session.save(teacher1);
            session.save(teacher2);

            session.save(klasa);
            session.save(klasa2);

            session.save(student);
            session.save(student2);
            session.save(student3);
            session.save(student4);

            session.save(rozsypankaGame);
            session.save(rozsypankaGame2);

            beginTransaction.commit();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

        // List<String> word = new ArrayList<>();
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // word.add("aaaaaaaaaaaaaaa");
        // WordSearchBordCreator bordCreator = new WordSearchBordCreator(word );
        // WordSearchBordTO createBord = bordCreator.createBord();
        //
        // // System.out.println(createBord);
        //
        // List<WordSearchRowTO> rows = createBord.getRows();
        //
        // for (WordSearchRowTO wordSearchRowTO : rows)
        // {
        // List<MappedLetterTO> row = wordSearchRowTO.getRow();
        // for (MappedLetterTO mappedLetterTO : row)
        // {
        // System.out.print(mappedLetterTO.getValue() + " ");
        // }
        // System.out.println();
        // }
        //
        // List<List<Integer>> solution = bordCreator.getSolution();
        //
        // for (List<Integer> list : solution)
        // {
        // System.out.println(list);
        // }
        //
        // System.out.println();
        //
        //
        // System.out.println(bordCreator.getSolution());
        // db = DBController.getInstance();
        // Session session = db.getSession();
        //
        // Teacher teacher1 = new Teacher(null, null, "pass", "admin");
        // Student student = new Student("name", "name2", 3, "pass", "log");
        //
        // RozsypankaGame game = new RozsypankaGame("game");
        // List<String> sent = new ArrayList<>();
        // sent.add("zdamo 1 ss asa");
        // game.setSentences(sent);
        //
        // GameResult gameResult = new GameResult();
        // gameResult.setStudent(student);
        // gameResult.setGame(game);
        // gameResult.setResult("2,344");
        //
        // GameResult gameResult2 = new GameResult();
        // gameResult.setStudent(student);
        // gameResult.setGame(game);
        // gameResult.setResult("2,344");
        //
        // try
        // {
        // Transaction beginTransaction = session.beginTransaction();
        //
        // session.save(teacher1);
        // session.save(student);
        // session.save(game);
        // session.save(gameResult);
        //
        // beginTransaction.commit();
        // }
        // catch (HibernateException e)
        // {
        // e.printStackTrace();
        // }
        // finally
        // {
        // session.close();
        // }
        //
        // session = db.getSession();
        // Transaction beginTransaction = session.beginTransaction();
        // List list =
        // session.createCriteria(GameResult.class,"r").createAlias("r.game",
        // "g")
        // .add(Restrictions.eq("g.id", 2)).list();
        // session.close();
        // System.out.println(list.size());
        // list.size();

        // List<String> senstences = new ArrayList<>();
        // senstences.add("takie sobie proste zdanie");
        // senstences.add("zdanie a animal the dziony an anyooi");
        // senstences.add("yoyo hehe");
        //
        // RozsypankaGame rozsypankaGame = new RozsypankaGame("xx");
        // rozsypankaGame.setSentences(senstences);
        //
        // CurrentRozsypankaGame createCurrRozsypanka =
        // CurrentGameCreator.createCurrRozsypanka(rozsypankaGame);
        //
        // System.out.println(createCurrRozsypanka.getSolution());
        // System.out.println(createCurrRozsypanka.getProcessRozsypanka());
        //
        // CurrentRozsypankaTO x = new CurrentRozsypankaTO();
        // x.setData(createCurrRozsypanka.getProcessRozsypanka());
        //
        // Teacher teacher1 = new Teacher("nameTeacher1",
        // "LastNameTeacher1","pas","t1");
        // Teacher teacher2 = new Teacher("nameTeacher2",
        // "LastNameTeacher2","pas","t2");
        //
        // Klasa klasaA = new Klasa("1A");
        // Klasa klasaB = new Klasa("1B");
        // Klasa klasaC = new Klasa("1C");
        //
        // Student student1 = new Student("nameStudent1", "lastname1",
        // 1,"pas","s1");
        // Student student2 = new Student("nameStudent2", "lastname1",
        // 2,"pas","s2");
        // Student student3 = new Student("nameStudent3", "lastname1",
        // 3,"pas","s3");
        // Student student4 = new Student("nameStudent4", "lastname1",
        // 4,"pas","s4");
        // Student student5 = new Student("nameStudent5", "lastname1",
        // 5,"pas","s5");
        // Student student6 = new Student("nameStudent6", "lastname1",
        // 6,"pas","s6");
        // Student student7 = new Student("nameStudent7", "lastname1",
        // 7,"pas","s7");
        // Student student8 = new Student("nameStudent8", "lastname1",
        // 8,"pas","s8");
        // Student student9 = new Student("nameStudent9", "lastname1",
        // 9,"pas","s9");
        // Student student10 = new Student("nameStudent10", "lastname1",
        // 10,"pas","s10");
        //
        // Tamagotchi animal1 = new Tamagotchi("zwierz1");
        // Tamagotchi animal2 = new Tamagotchi("zwierz2");
        // Tamagotchi animal3 = new Tamagotchi("zwierz3");
        // Tamagotchi animal4 = new Tamagotchi("zwierz4");
        // Tamagotchi animal5 = new Tamagotchi("zwierz5");
        // Tamagotchi animal6 = new Tamagotchi("zwierz6");
        // Tamagotchi animal7 = new Tamagotchi("zwierz7");
        // Tamagotchi animal8 = new Tamagotchi("zwierz8");
        // Tamagotchi animal9 = new Tamagotchi("zwierz9");
        // Tamagotchi animal10 = new Tamagotchi("zwierz10");
        //
        // // DBUtils.connectTeacherStudent(teacher1, student1, student2,
        // student3, student4, student5);
        // // DBUtils.connectTeacherStudent(teacher2, student6, student7,
        // student8, student9, student10);
        //
        // DBUtils.connectStudentsToClass(klasaA, student1, student2, student3,
        // student4, student5);
        // DBUtils.connectStudentsToClass(klasaB, student6, student7);
        // DBUtils.connectStudentsToClass(klasaC, student8, student9,
        // student10);
        //
        // DBUtils.connectStudentToAnimal(student1, animal1);
        // DBUtils.connectStudentToAnimal(student2, animal2);
        // DBUtils.connectStudentToAnimal(student3, animal3);
        // DBUtils.connectStudentToAnimal(student4, animal4);
        // DBUtils.connectStudentToAnimal(student5, animal5);
        // DBUtils.connectStudentToAnimal(student6, animal6);
        // DBUtils.connectStudentToAnimal(student7, animal7);
        // DBUtils.connectStudentToAnimal(student8, animal8);
        // DBUtils.connectStudentToAnimal(student9, animal9);
        // DBUtils.connectStudentToAnimal(student10, animal10);
        //
        // DBUtils.connectClassesToTeacher(teacher1, klasaA);
        // DBUtils.connectClassesToTeacher(teacher2, klasaB, klasaC);
        //
        // Set<Object> toSave = new HashSet<>();
        // toSave.add(student1);
        // toSave.add(student2);
        // toSave.add(student3);
        // toSave.add(student4);
        // toSave.add(student5);
        // toSave.add(student6);
        // toSave.add(student7);
        // toSave.add(student8);
        // toSave.add(student9);
        // toSave.add(student10);
        //
        // toSave.add(animal1);
        // toSave.add(animal2);
        // toSave.add(animal3);
        // toSave.add(animal4);
        // toSave.add(animal5);
        // toSave.add(animal6);
        // toSave.add(animal7);
        // toSave.add(animal8);
        // toSave.add(animal9);
        // toSave.add(animal10);
        //
        // toSave.add(klasaA);
        // toSave.add(klasaB);
        // toSave.add(klasaC);
        //
        // toSave.add(teacher1);
        // toSave.add(teacher2);
        //
        //
        // db = DBController.getInstance();
        //
        // db.save(toSave.toArray());

        // db = DBController.getInstance();
        //
        // Session session = db.getSession();
        //
        // Transaction beginTransaction = session.beginTransaction();
        //
        // Teacher t = (Teacher) session.get(Teacher.class, 15);
        // Student s = (Student) session.get(Student.class, 16);
        //
        // RozsypankaGame game = new RozsypankaGame("rozsypanka");
        // game.getSentences().add("Jakies spoko zdanie1");
        // game.getSentences().add("Jakies spoko zdanie2");
        // game.getSentences().add("Jakies spoko zdanie3");
        //
        //
        // RozsypankaGame game1 = new RozsypankaGame("rozsypanka2");
        // game1.getSentences().add("zdanie1");
        // game1.getSentences().add("zdanie2");
        // game1.getSentences().add("zdanie3");
        //
        //
        // t.getGames().add(game);
        // t.getGames().add(game1);
        //
        //
        // GameResult gameResult = new GameResult();
        // GameResult gameResult2 = new GameResult();
        //
        // gameResult.setScore(20);
        // gameResult.setGame(game);
        //
        // gameResult2.setScore(30);
        // gameResult2.setGame(game1);
        //
        // s.getGameHistory().add(gameResult);
        // s.getGameHistory().add(gameResult2);
        //
        // // session.saveOrUpdate(t);
        // // session.saveOrUpdate(s);
        // session.saveOrUpdate(gameResult2);
        // session.saveOrUpdate(gameResult);
        //
        // beginTransaction.commit();
        //
        // session.close();

        //
        // t.getGames().add(game);
        // game.setOwner(t);
        //
        // session.close();
        // db.save(game);
        // db.update(t);

        // // session.close();
        // RestHelper helper = new RestHelper();
        //
        // Klasa classById = helper.getClassById(6);
        // //
        // Teacher teacher = classById.getTeacher();
        // teacher.getFirstName();
        //
        // System.out.println(teacher.getFirstName());
        //
        // Set<Student> students = classById.getStudents();
        // for (Student student : students)
        // {
        // System.out.println(student.getFirstName());
        // }

    }

}
