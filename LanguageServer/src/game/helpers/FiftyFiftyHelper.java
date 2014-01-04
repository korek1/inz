package game.helpers;

import utils.CommonUtils;

public class FiftyFiftyHelper {

    private Integer usedForQuestion;
    private Integer rejectedAns1;
    private Integer rejectedAns2;

    public FiftyFiftyHelper()
    {
        super();
    }

    public FiftyFiftyHelper(Integer usedForQuestion)
    {
        super();
        this.usedForQuestion = usedForQuestion;
    }
    
    public boolean addRejectedAns(int i)
    {
        boolean added = false;
        
        if(CommonUtils.isNull(rejectedAns1))
        {
            rejectedAns1 = new Integer(i);
            added = true;
        }
        else if(CommonUtils.isNull(rejectedAns2) && !rejectedAns1.equals(i))
        {
            rejectedAns2 = new Integer(i);
            added = true;
        }
        
        return added;
    }
    
    public boolean usedForThisQuestion(Integer question)
    {
        boolean usedForThis = false;
        
        if(question.equals(usedForQuestion))
        {
            usedForThis = true;
        }
        
        return usedForThis;
    }

    public Integer getRejectedAns1()
    {
        return rejectedAns1;
    }

    public Integer getRejectedAns2()
    {
        return rejectedAns2;
    }

}
