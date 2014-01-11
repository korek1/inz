package game;

import game.data.GameDataParser;
import game.helpers.HangManSolutionHelper;
import game.helpers.WordSearchBordCreator;
import game.impl.CurrentHangManGame;
import game.impl.CurrentMemoGame;
import game.impl.CurrentMillionaireGame;
import game.impl.CurrentRozsypankaGame;
import game.impl.CurrentSpellGame;
import game.impl.CurrentWordSearchGame;
import game.to.TOsGameManager;
import game.to.hangman.HangManStudentTO;
import game.to.hangman.HangManTO;
import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.rozsypanka.MappedWordTO;
import game.to.rozsypanka.RozsypankaGameStudentTO;
import game.to.spell.SpellGameStudentTO;
import game.to.spell.SpellGameTO;
import game.to.spell.SpellPairStudentTO;
import game.to.spell.SpellPairTO;
import game.to.wordsearch.WordSearchBordTO;
import game.to.wordsearch.WordSearchGameStudentTO;
import game.to.wordsearch.WordSearchGameTO;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import utils.CommonUtils;
import utils.FileUtils;
import dto.games.HangManGame;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.SpellGame;
import dto.games.WordSearchGame;
import dto.games.model.PicWordPair;

public class CurrentGameCreator {

    public static FormDataMultiPart createAndStartCurrMemo(MemoGame memoGame, String login)
    {
        CurrentMemoGame currentMemoGame = new CurrentMemoGame(memoGame);
        setStartDate(currentMemoGame);

        List<PicWordPair> picWordPairList = memoGame.getPicWordPair();

        List<MappedWordTO> processMemo = GameDataParser.processMemo(picWordPairList);

        List<List<Integer>> solutionToRemember = new ArrayList<>();

        for (MappedWordTO mappedWordTO : processMemo)
        {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(mappedWordTO.getMappedValue());
            solutionToRemember.add(arrayList);
        }

        currentMemoGame.setSolution(solutionToRemember);

        GameHelper.startGame(login, currentMemoGame);

        Collections.shuffle(processMemo);
        FormDataMultiPart multiPart = new FormDataMultiPart();
        int i = 0;
        for (PicWordPair picWordPair : picWordPairList)
        {
            MappedWordTO mappedWordTO = processMemo.get(i++);
            Integer mappedValue = mappedWordTO.getMappedValue();
            String word = mappedWordTO.getWord();

            String string = word + "=" + mappedValue;
            String picPath = picWordPair.getPicPath();
            String extension = FileUtils.getExtension(picPath);
            File pic = new File(picPath);

            multiPart.field(string, (Object) pic, new MediaType("image", extension));

        }

        return multiPart;

    }

    public static RozsypankaGameStudentTO createAndStartCurrRozsypanka(RozsypankaGame rozsypankaGame, String login)
    {
        CurrentRozsypankaGame currRozsypanka = new CurrentRozsypankaGame(rozsypankaGame);

        setStartDate(currRozsypanka);
        List<String> sentences = rozsypankaGame.getSentences();

        List<List<MappedWordTO>> processRozsypanka = GameDataParser.processRozsypanka(sentences);

        List<List<Integer>> solutionToRemember = new ArrayList<>();

        for (List<MappedWordTO> list : processRozsypanka)
        {
            List<Integer> temp = new ArrayList<>();
            for (MappedWordTO mappedWordTO : list)
            {
                temp.add(mappedWordTO.getMappedValue());
            }
            solutionToRemember.add(temp);
        }

        currRozsypanka.setSolution(solutionToRemember);

        GameHelper.startGame(login, currRozsypanka);

        RozsypankaGameStudentTO rozsypankaGameStudentTO = TOsGameManager.processRozsypankaForStudent(rozsypankaGame, processRozsypanka);

        return rozsypankaGameStudentTO;
    }

    public static MillionaireGameTO createAndStartCurrMillionaire(MillionaireGame millionaireGame, String login)
    {
        CurrentMillionaireGame currentMillionaireGame = new CurrentMillionaireGame(millionaireGame);
        setStartDate(currentMillionaireGame);

        MillionaireGameTO convertMillionaireGame = TOsGameManager.convertMillionaireGame(millionaireGame);
        List<MillionaireQuestionTO> questions = convertMillionaireGame.getQuestions();
        Collections.shuffle(questions);

        List<List<Integer>> solutionToRemember = new ArrayList<>();
        for (MillionaireQuestionTO millionaireQuestionTO : questions)
        {
            Integer correctAnswer = millionaireQuestionTO.getCorrectAnswer();

            List<Integer> temp = new ArrayList<>();
            temp.add(correctAnswer);

            solutionToRemember.add(temp);

            millionaireQuestionTO.setCorrectAnswer(null);
        }

        currentMillionaireGame.setSolution(solutionToRemember);

        GameHelper.startGame(login, currentMillionaireGame);

        return convertMillionaireGame;
    }

    public static WordSearchGameStudentTO createAndStartCurrWordSearch(WordSearchGame wordSearchGame, String login, int widthBoard, int heightBoard)
    {

        CurrentWordSearchGame currentWordSearchGame = new CurrentWordSearchGame(wordSearchGame);
        setStartDate(currentWordSearchGame);

        WordSearchGameTO wordSearchGameTO = TOsGameManager.convertSearchGame(wordSearchGame);

        WordSearchGameStudentTO wordSearchGameForStudentTO = TOsGameManager.covertWordSearchGameForStudent(wordSearchGameTO);

        List<String> angWords = wordSearchGameTO.getAngWords();
        List<String> polWords = wordSearchGameTO.getPolWords();

        WordSearchBordCreator bordCreator = new WordSearchBordCreator(angWords, polWords);
        WordSearchBordTO createBord = bordCreator.createBord(widthBoard, heightBoard);

        wordSearchGameForStudentTO.setBord(createBord);
        wordSearchGameForStudentTO.setPolWords(bordCreator.getPolMappedWords());

        currentWordSearchGame.setSolution(bordCreator.getSolution());

        GameHelper.startGame(login, currentWordSearchGame);

        return wordSearchGameForStudentTO;
    }

    public static SpellGameStudentTO createAndStartCurrSpellGame(SpellGame spellGame, String login)
    {

        CurrentSpellGame currentSpellGame = new CurrentSpellGame(spellGame);
        setStartDate(currentSpellGame);

        SpellGameTO spellGameTO = TOsGameManager.convertSpellGame(spellGame);
        SpellGameStudentTO spellGameStudentTO = new SpellGameStudentTO(spellGameTO);

        List<List<Integer>> solution = new ArrayList<>();

        for (SpellPairTO pairTO : spellGameTO.getWords())
        {
            String wordOk = pairTO.getWordOk();
            String wordWrong = pairTO.getWordWrong();
            String polWord = pairTO.getPolWord();

            int rand = CommonUtils.rand(2);

            List<Integer> partSolution = new ArrayList<>();
            if (rand == 1)
            {
                spellGameStudentTO.addPairTO(new SpellPairStudentTO(wordOk, wordWrong, polWord));
            }
            else
            {
                spellGameStudentTO.addPairTO(new SpellPairStudentTO(wordWrong, wordOk, polWord));
            }
            partSolution.add(rand);
            solution.add(partSolution);
        }

        currentSpellGame.setSolution(solution);

        GameHelper.startGame(login, currentSpellGame);

        return spellGameStudentTO;
    }

    public static HangManStudentTO createAndStartCurrHangMan(HangManGame hangManGame, String login)
    {
        CurrentHangManGame currentHangManGame = new CurrentHangManGame(hangManGame);
        setStartDate(currentHangManGame);

        HangManTO hangManGameTO = TOsGameManager.convertHangManGame(hangManGame);
        HangManStudentTO hangManStudentTO = new HangManStudentTO(hangManGameTO);

        List<List<Integer>> solution = new ArrayList<>();

        for (String word : hangManGameTO.getWords())
        {
            hangManStudentTO.addWordLength(word.length());
            List<Integer> partSolution = HangManSolutionHelper.createPartSolution(word);
            solution.add(partSolution);
        }

        currentHangManGame.setSolution(solution);

        GameHelper.startGame(login, currentHangManGame);

        return hangManStudentTO;

    }

    private static void setStartDate(CurrentGame currGame)
    {
        currGame.setStartTime(new Date());
    }

}
