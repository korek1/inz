package game;

import game.data.GameDataParser;
import game.helpers.WordSearchBordCreator;
import game.impl.CurrentMemoGame;
import game.impl.CurrentMillionaireGame;
import game.impl.CurrentRozsypankaGame;
import game.impl.CurrentWordSearchGame;
import game.to.TOsGameManager;
import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.rozsypanka.MappedWordTO;
import game.to.rozsypanka.RozsypankaGameStudentTO;
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

import utils.FileUtils;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
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

        List<String> words = wordSearchGameTO.getWords();

        WordSearchBordCreator bordCreator = new WordSearchBordCreator(words);
        WordSearchBordTO createBord = bordCreator.createBord(widthBoard, heightBoard);

        wordSearchGameForStudentTO.setBord(createBord);

        currentWordSearchGame.setSolution(bordCreator.getSolution());

        GameHelper.startGame(login, currentWordSearchGame);

        return wordSearchGameForStudentTO;
    }

    private static void setStartDate(CurrentGame currGame)
    {
        currGame.setStartTime(new Date());
    }
    
    

}
