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
import dto.games.GameCategory;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.WordSearchGame;
import dto.games.model.MillionaireQuestion;
import dto.to.GameCategoryTO;
import dto.to.GameCategoryTOs;

public class TOsGameManager {
    
    public static final GameCategoryTOs GAME_CATEGORIES = getGameCategories();

    public static RozsypankaGameTO convertRozsypankaGame(RozsypankaGame rozsypankaGame)
    {

        RozsypankaGameTO rozsypankaGameTO = new RozsypankaGameTO();
        convertGame(rozsypankaGameTO, rozsypankaGame);

        rozsypankaGameTO.setSentences(rozsypankaGame.getSentences());

        return rozsypankaGameTO;

    }

    public static RozsypankaGame convertRozsypankaGameTO(RozsypankaGameTO rozsypankaGameTO)
    {
        RozsypankaGame rozsypankaGame = new RozsypankaGame();
        convertGameTO(rozsypankaGame, rozsypankaGameTO);

        List<String> sentences = rozsypankaGameTO.getSentences();
        rozsypankaGame.setSentences(sentences);

        return rozsypankaGame;

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

    public static WordSearchGame covertWordSearchGameTO(WordSearchGameTO wordSearchGameTO)
    {
        WordSearchGame wordSearchGame = new WordSearchGame();
        convertGameTO(wordSearchGame, wordSearchGameTO);
        
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
        convertGame(wordSearchGameTO, wordSearchGame);

        String wordsDB = wordSearchGame.getWords();
        String[] split = wordsDB.split("#");

        List<String> words = new ArrayList<>();
        for (int i = 1; i < split.length; i++)
        {
            words.add(split[i]);
        }

        wordSearchGameTO.setWords(words);

        return wordSearchGameTO;
    }

    public static MillionaireGameTO convertMillionaireGame(MillionaireGame millionaireGame)
    {
        MillionaireGameTO millionaireGameTO = new MillionaireGameTO();
        convertGame(millionaireGameTO, millionaireGame);

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
        convertGameTO(millionaireGame, millionaireGameTO);

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

    private static GameTO processGame(Game game)
    {
        GameTO gameTO = new GameTO();
        gameTO.setId(game.getId());
        gameTO.setName(game.getName());

        return gameTO;
    }

    private static void convertGame(GameTO gameTO, Game game)
    {
        int id = game.getId();
        String name = game.getName();
        int categoryId = game.getCategory();

        categoryId = GameCategory.validateCategory(categoryId);

        gameTO.setCategoryId(categoryId);
        gameTO.setId(id);
        gameTO.setName(name);
    }

    private static void convertGameTO(Game game, GameTO gameTO)
    {
        int categoryId = gameTO.getCategoryId();
        String name = gameTO.getName();

        categoryId = GameCategory.validateCategory(categoryId);

        game.setCategory(categoryId);
        game.setName(name);

    }
    
    private static GameCategoryTOs getGameCategories()
    {
        GameCategoryTOs categories = new GameCategoryTOs();

        GameCategory[] values = GameCategory.values();
        for (GameCategory gameCategory : values)
        {
            int id = gameCategory.getId();
            String name = gameCategory.getName();

            GameCategoryTO categoryTO = new GameCategoryTO(name, id);
            categories.addCategoryTO(categoryTO);
        }
        
        return categories;
    }

}