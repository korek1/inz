package game.helpers;

import dto.Game;
import dto.games.HangManGame;
import dto.games.MemoGame;
import dto.games.MillionaireGame;
import dto.games.RozsypankaGame;
import dto.games.SpellGame;
import dto.games.WordSearchGame;

public enum GameTypeEnum
{
    ROZSYPANKA("rozsypanka", RozsypankaGame.class),
    
    MEMO("memo", MemoGame.class),
    
    WORD_SEARCH("wordsearch", WordSearchGame.class),
    
    MILLIONAIRE("millionaire", MillionaireGame.class),
    
    SPELL("spell",SpellGame.class),
    
    HANG_MAN("hangman",HangManGame.class);
    
    private GameTypeEnum(String path, Class<? extends Game> clazz)
    {
        this.path = path;
        this.clazz = clazz;
    }

    private final String path;
    private final Class<? extends Game> clazz;

    public String getPath()
    {
        return path;
    }
    
    public Class<? extends Game> getClazz()
    {
        return clazz;
    }

    public GameTypeEnum getTypeByClass(Class<? extends Game> clazz)
    {
        GameTypeEnum gameType = null ;
        
        GameTypeEnum[] values = GameTypeEnum.values();
        for (GameTypeEnum gameTypeEnum : values)
        {
            if(gameTypeEnum.getClazz().equals(clazz))
            {
                gameType = gameTypeEnum;
                break;
            }
        }
        
        return gameType;
    }
}
