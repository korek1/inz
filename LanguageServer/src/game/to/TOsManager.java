package game.to;

import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.rozsypanka.MappedWordTO;
import game.to.rozsypanka.RozsypankaGameStudentTO;
import game.to.rozsypanka.RozsypankaGameTO;
import game.to.rozsypanka.RozsypankaGameTOs;
import game.to.wordsearch.WordSearchGameStudentTO;
import game.to.wordsearch.WordSearchGameTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Game;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.WordSearchGame;
import dto.games.model.MillionaireQuestion;

public class TOsManager {

    public static RozsypankaGameTO processRozsypanka(RozsypankaGame rozsypankaGame, boolean setSentences)
    {
        RozsypankaGameTO rozsypankaGameTO = new RozsypankaGameTO();
        rozsypankaGameTO.setId(rozsypankaGame.getId());
        rozsypankaGameTO.setName(rozsypankaGame.getName());
        if (setSentences)
        {
            rozsypankaGameTO.setSentences(rozsypankaGame.getSentences());
        }
        return rozsypankaGameTO;

    }

    public static RozsypankaGameTO processRozsypanka(RozsypankaGame rozsypankaGame)
    {

        return processRozsypanka(rozsypankaGame, true);

    }

    public static RozsypankaGameTOs processRozsypankas(List<Game> rozsypankaGames)
    {
        RozsypankaGameTOs rozsypankaGamesTO = new RozsypankaGameTOs();

        for (Game rozsypankaGame : rozsypankaGames)
        {
            RozsypankaGameTO processRozsypanka = processRozsypanka((RozsypankaGame) rozsypankaGame, false);
            rozsypankaGamesTO.addRozsypankaTO(processRozsypanka);

        }

        return rozsypankaGamesTO;
    }

    public static RozsypankaGameStudentTO processRozsypankaForStudent(List<List<MappedWordTO>> processRozsypanka)
    {
        RozsypankaGameStudentTO rozsypankaGameTO = null;

        for (List<MappedWordTO> list : processRozsypanka)
        {
            Collections.shuffle(list);
        }

        rozsypankaGameTO = new RozsypankaGameStudentTO(processRozsypanka);

        return rozsypankaGameTO;

    }

    public static GameTOs processGames(List<Game> gameDB)
    {
        GameTOs gameTOs = new GameTOs();

        for (Game game : gameDB)
        {
            GameTO gameTO = processGame(game);
            gameTOs.add(gameTO);
        }

        return gameTOs;
    }

    public static WordSearchGame covertWordSearchGameTO(WordSearchGameTO wordSearchGameTO)
    {
        WordSearchGame wordSearchGame = new WordSearchGame();
        wordSearchGame.setName(wordSearchGameTO.getName());

        String wordsDB = "";
        List<String> words = wordSearchGameTO.getWords();
        for (String string : words)
        {
            wordsDB += ("#" + string);
        }
        wordSearchGame.setWords(wordsDB);

        return wordSearchGame;
    }

    public static WordSearchGameTO convertSearchGame(WordSearchGame wordSearchGame)
    {
        WordSearchGameTO wordSearchGameTO = new WordSearchGameTO();

        int id = wordSearchGame.getId();
        String name = wordSearchGame.getName();
        String wordsDB = wordSearchGame.getWords();
        String[] split = wordsDB.split("#");

        List<String> words = new ArrayList<>();
        for (int i = 1; i < split.length; i++)
        {
            words.add(split[i]);
        }

        wordSearchGameTO.setId(id);
        wordSearchGameTO.setName(name);
        wordSearchGameTO.setWords(words);
        return wordSearchGameTO;
    }

    public static MillionaireGameTO convertMillionaireGame(MillionaireGame millionaireGame)
    {
        MillionaireGameTO millionaireGameTO = new MillionaireGameTO();

        int id = millionaireGame.getId();
        String name = millionaireGame.getName();
        millionaireGameTO.setId(id);
        millionaireGameTO.setName(name);

        List<MillionaireQuestion> questions = millionaireGame.getQuestions();
        for (MillionaireQuestion millionaireQuestion : questions)
        {
            String question = millionaireQuestion.getQuestion();
            String answer1 = millionaireQuestion.getAnswer1();
            String answer2 = millionaireQuestion.getAnswer2();
            String answer3 = millionaireQuestion.getAnswer3();
            String answer4 = millionaireQuestion.getAnswer4();
            int correctAnswer = millionaireQuestion.getCorrectAnswer();

            MillionaireQuestionTO millionaireQuestionTO = new MillionaireQuestionTO();
            millionaireQuestionTO.setCorrectAnswer(correctAnswer);
            millionaireQuestionTO.setQuestion(question);
            millionaireQuestionTO.addAnswer(answer1);
            millionaireQuestionTO.addAnswer(answer2);
            millionaireQuestionTO.addAnswer(answer3);
            millionaireQuestionTO.addAnswer(answer4);

            millionaireGameTO.addQuestion(millionaireQuestionTO);
        }

        return millionaireGameTO;

    }

    public static MillionaireGame convertMillionaireGameTO(MillionaireGameTO millionaireGameTO)
    {
        MillionaireGame millionaireGame = new MillionaireGame();

        String name = millionaireGameTO.getName();
        millionaireGame.setName(name);

        List<MillionaireQuestion> questions = new ArrayList<>();
        List<MillionaireQuestionTO> questionsTO = millionaireGameTO.getQuestions();
        for (MillionaireQuestionTO millionaireQuestionTO : questionsTO)
        {
            MillionaireQuestion millionaireQuestion = new MillionaireQuestion();
            String question = millionaireQuestionTO.getQuestion();
            Integer correctAnswer = millionaireQuestionTO.getCorrectAnswer();
            List<String> answers = millionaireQuestionTO.getAnswers();

            millionaireQuestion.setAnswer1(answers.get(0));
            millionaireQuestion.setAnswer2(answers.get(1));
            millionaireQuestion.setAnswer3(answers.get(2));
            millionaireQuestion.setAnswer4(answers.get(3));

            millionaireQuestion.setCorrectAnswer(correctAnswer);
            millionaireQuestion.setQuestion(question);
            millionaireQuestion.setGame(millionaireGame);
            questions.add(millionaireQuestion);
        }

        millionaireGame.setQuestions(questions);

        return millionaireGame;
    }
    
    public static WordSearchGameStudentTO covertWordSearchGameForStudent(WordSearchGameTO wordSearchGameTO)
    {
        WordSearchGameStudentTO wordSearchGameStudentTO = new WordSearchGameStudentTO();
        wordSearchGameStudentTO.setName(wordSearchGameTO.getName());
        wordSearchGameStudentTO.setId(wordSearchGameTO.getId());
        
        return wordSearchGameStudentTO;
    }

    private static GameTO processGame(Game game)
    {
        GameTO gameTO = new GameTO();
        gameTO.setId(game.getId());
        gameTO.setName(game.getName());

        return gameTO;
    }

}
