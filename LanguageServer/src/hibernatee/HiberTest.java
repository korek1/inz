package hibernatee;

import game.helpers.MemoDirHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dto.Klasa;
import dto.Student;
import dto.Teacher;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.SpellGame;
import dto.games.model.MillionaireQuestion;
import dto.games.model.SpellPair;

public class HiberTest {

    static DBController db;

    public static void main(String[] args) throws IOException
    {
     //   clearPaths();

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
        
        MillionaireGame millionaireGame = new MillionaireGame();
        millionaireGame.setCategory(3);
        millionaireGame.setDifficultyFactor(4);
        millionaireGame.setName("nameeee");
        millionaireGame.setOwner(teacher2);
        
        List<MillionaireQuestion> questions = new ArrayList<>();
        MillionaireQuestion question1 = new MillionaireQuestion();
        question1.setAnswer1("tak");
        question1.setAnswer2("nie");
        question1.setAnswer3("nie");
        question1.setAnswer4("nie");
        question1.setCorrectAnswer(1);
        
        question1.setQuestion("pytanie");
        question1.setGame(millionaireGame);
        
        questions.add(question1 );
        
        
        MillionaireQuestion question2 = new MillionaireQuestion();
        question2.setAnswer1("nie");
        question2.setAnswer2("nie");
        question2.setAnswer3("nie");
        question2.setAnswer4("tak");
        question2.setCorrectAnswer(4);
        
        question2.setQuestion("pytanie");
        question2.setGame(millionaireGame);
        
        questions.add(question2 );
        millionaireGame.setQuestions(questions );
        
        SpellGame spellGame = new SpellGame();
        spellGame.setCategory(4);
        spellGame.setDifficultyFactor(10);
        spellGame.setName("spellgame");
        spellGame.setOwner(teacher2);
        
        List<SpellPair> words = new ArrayList<>();
        SpellPair pair = new SpellPair();
        pair.setGame(spellGame);
        pair.setPolWord("kot");
        pair.setWordOk("cat");
        pair.setWordWrong("coooot");
        
        SpellPair pair2 = new SpellPair();
        pair2.setGame(spellGame);
        pair2.setPolWord("pies");
        pair2.setWordOk("dog");
        pair2.setWordWrong("daaag");
        
        SpellPair pair3 = new SpellPair();
        pair3.setGame(spellGame);
        pair3.setPolWord("robak");
        pair3.setWordOk("bug");
        pair3.setWordWrong("buk");
        words.add(pair);
        words.add(pair2);
        words.add(pair3);
        
        spellGame.setWords(words );
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

            session.save(millionaireGame);
            session.save(spellGame);
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

    }
    
    private static void clearPaths() throws IOException
    {
        String dir = MemoDirHelper.BASE_DIR;
        
        System.out.println(dir);
        
        FileUtils.deleteDirectory(new File(dir));
    }

}
