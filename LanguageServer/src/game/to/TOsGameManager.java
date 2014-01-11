package game.to;

import game.to.hangman.HangManTO;
import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.rozsypanka.MappedWordTO;
import game.to.rozsypanka.RozsypankaGameStudentTO;
import game.to.rozsypanka.RozsypankaGameTO;
import game.to.spell.SpellGameTO;
import game.to.spell.SpellPairTO;
import game.to.wordsearch.WordSearchGameStudentTO;
import game.to.wordsearch.WordSearchGameTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Game;
import dto.games.GameCategory;
import dto.games.HangManGame;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.SpellGame;
import dto.games.WordSearchGame;
import dto.games.model.MillionaireQuestion;
import dto.games.model.SpellPair;
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

    public static RozsypankaGameStudentTO processRozsypankaForStudent(Game game, List<List<MappedWordTO>> processRozsypanka)
    {

        for (List<MappedWordTO> list : processRozsypanka)
        {
            Collections.shuffle(list);
        }
        RozsypankaGameStudentTO rozsypankaGameTO = new RozsypankaGameStudentTO(processRozsypanka);;
        convertGame(rozsypankaGameTO, game);

        return rozsypankaGameTO;

    }

    public static WordSearchGame covertWordSearchGameTO(WordSearchGameTO wordSearchGameTO)
    {
        WordSearchGame wordSearchGame = new WordSearchGame();
        convertGameTO(wordSearchGame, wordSearchGameTO);

        String wordsDB = "";
        List<String> angWords = wordSearchGameTO.getAngWords();
        List<String> polWords = wordSearchGameTO.getPolWords();

        for (int i = 0; i < angWords.size(); i++)
        {
            String angWord = angWords.get(i);
            String polWord = polWords.get(i);

            wordsDB += ("#" + angWord + "#" + polWord);
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

        List<String> angWords = new ArrayList<>();
        List<String> polWords = new ArrayList<>();
        for (int i = 1; i < split.length; i++)
        {
            if (i % 2 == 1)
            {
                angWords.add(split[i]);
            }
            else
            {
                polWords.add(split[i]);
            }

        }

        wordSearchGameTO.setAngWords(angWords);
        wordSearchGameTO.setPolWords(polWords);

        return wordSearchGameTO;
    }

    public static SpellGame covertSpellGameTO(SpellGameTO spellGameTO)
    {
        SpellGame spellGame = new SpellGame();
        convertGameTO(spellGame, spellGameTO);

        List<SpellPair> wordsDB = new ArrayList<>();
        List<SpellPairTO> words = spellGameTO.getWords();

        for (SpellPairTO spellPairTO : words)
        {
            SpellPair pair = new SpellPair();

            pair.setWordOk(spellPairTO.getWordOk());
            pair.setWordWrong(spellPairTO.getWordWrong());
            pair.setPolWord(spellPairTO.getPolWord());

            pair.setGame(spellGame);
            wordsDB.add(pair);
        }

        spellGame.setWords(wordsDB);

        return spellGame;
    }

    public static SpellGameTO convertSpellGame(SpellGame spellGame)
    {
        SpellGameTO spellGameTO = new SpellGameTO();
        convertGame(spellGameTO, spellGame);

        List<SpellPair> words = spellGame.getWords();
        for (SpellPair spellPair : words)
        {
            spellGameTO.addPair(new SpellPairTO(spellPair.getWordOk(), spellPair.getWordWrong(), spellPair.getPolWord()));
        }

        return spellGameTO;
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

    public static HangManGame convertHangManGameTO(HangManTO hangManTO)
    {
        HangManGame hangManGame = new HangManGame();
        convertGameTO(hangManGame, hangManTO);

        List<String> words = hangManTO.getWords();
        String toDB = "";
        for (String word : words)
        {
            toDB += ("#" + word);
        }

        hangManGame.setWords(toDB);

        return hangManGame;
    }

    public static HangManTO convertHangManGame(HangManGame hangManGame)
    {
        HangManTO hangManTO = new HangManTO();
        convertGame(hangManTO, hangManGame);

        String words = hangManGame.getWords();
        String[] split = words.split("#");
        for (int i = 1; i < split.length; i++)
        {
            hangManTO.addWord(split[i]);
        }

        return hangManTO;

    }

    public static WordSearchGameStudentTO covertWordSearchGameForStudent(WordSearchGameTO wordSearchGameTO)
    {
        WordSearchGameStudentTO wordSearchGameStudentTO = new WordSearchGameStudentTO(wordSearchGameTO);

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
        convertGame(gameTO, game);

        return gameTO;
    }

    public static MemoGame memoConvert(GameTO gameTO)
    {
        MemoGame memoGame = new MemoGame();

        convertGame(gameTO, memoGame);

        return memoGame;
    }

    private static void convertGame(GameTO gameTO, Game game)
    {
        int id = game.getId();
        String name = game.getName();
        int categoryId = game.getCategory();
        int difficultyFactor = game.getDifficultyFactor();

        categoryId = GameCategory.validateCategory(categoryId);

        gameTO.setCategoryId(categoryId);
        gameTO.setId(id);
        gameTO.setName(name);
        gameTO.setDifficultyFactor(difficultyFactor);
    }

    private static void convertGameTO(Game game, GameTO gameTO)
    {
        int categoryId = gameTO.getCategoryId();
        String name = gameTO.getName();
        int difficultyFactor = gameTO.getDifficultyFactor();

        categoryId = GameCategory.validateCategory(categoryId);

        game.setCategory(categoryId);
        game.setName(name);
        game.setDifficultyFactor(difficultyFactor);

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
