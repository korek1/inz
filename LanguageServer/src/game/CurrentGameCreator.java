package game;

import game.data.GameDataParser;
import game.impl.CurrentMemoGame;
import game.impl.CurrentMillionaireGame;
import game.impl.CurrentRozsypankaGame;
import game.impl.CurrentWordSearchGame;
import game.to.TOsManager;
import game.to.millionaire.MillionaireGameTO;
import game.to.millionaire.MillionaireQuestionTO;
import game.to.rozsypanka.MappedWordTO;
import game.to.rozsypanka.RozsypankaGameStudentTO;
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
import dto.games.model.PicWordPair;

public class CurrentGameCreator {

    public static FormDataMultiPart createAndStartCurrMemo(MemoGame memoGame, String login)
    {
        CurrentMemoGame currentMemoGame = new CurrentMemoGame();
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
        CurrentRozsypankaGame currRozsypanka = new CurrentRozsypankaGame();

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

        RozsypankaGameStudentTO rozsypankaGameStudentTO = TOsManager.processRozsypankaForStudent(processRozsypanka);

        return rozsypankaGameStudentTO;
    }

    public static MillionaireGameTO createAndStartCurrMillionaire(MillionaireGame millionaireGame, String login)
    {
        CurrentMillionaireGame currentMillionaireGame = new CurrentMillionaireGame();
        setStartDate(currentMillionaireGame);

        MillionaireGameTO convertMillionaireGame = TOsManager.convertMillionaireGame(millionaireGame);
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

    public static void createAndStartCurrWordSearch(WordSearchGameTO wordSearchGameTO, String login)
    {

        CurrentWordSearchGame currentWordSearchGame = new CurrentWordSearchGame();
        setStartDate(currentWordSearchGame);

        List<String> words = wordSearchGameTO.getWords();
        currentWordSearchGame.setWordsSolution(words);
        GameHelper.startGame(login, currentWordSearchGame);
    }

    private static void setStartDate(CurrentGame currGame)
    {
        currGame.setStartTime(new Date());
    }

}
