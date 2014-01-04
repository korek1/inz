package game.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.CommonUtils;

public class Lifelines {

    private boolean fiftyFifty = true;
    private boolean askAudience = true;
    private boolean askFriend = true;
    
    private Set<Integer> usedQuestions = new HashSet<>();

    private List<Integer> correctAnswers = new ArrayList<>();

    public Lifelines(List<List<Integer>> solution)
    {
        super();
        for (List<Integer> list : solution)
        {
            correctAnswers.add(list.get(0));
        }
    }

    public String produceFiftyFifty(int questionNoumber)
    {
        String toReturn = "";

        if (fiftyFifty && usedQuestions.add(questionNoumber))
        {
            Integer answer = correctAnswers.get(questionNoumber - 1);

            int count = 0;
            while (count != 2)
            {
                int rand = CommonUtils.rand(4);
                if (!answer.equals(rand))
                {
                    toReturn += (rand + " ");
                    count++;
                }

            }

            fiftyFifty = false;
        }

        return toReturn;
    }

    public String produceAskAudience(int questionNoumber) 
    {
        String toReturn = "";
        
        if (askAudience && usedQuestions.add(questionNoumber))
        {
            Integer answer = correctAnswers.get(questionNoumber - 1);
            
            int threshold = 0;
            int rand = CommonUtils.rand(3);
          
            if(rand == 1)
            {
                threshold = 25;
            }
            else if(rand == 2)
            {
                threshold = 35;
            }
            else
            {
                threshold = 45;
            }
            
            int forCorrect = CommonUtils.rand(98 - threshold) + threshold;
            
            int forAns1 = CommonUtils.rand(99 - forCorrect);
          
            int forAns2 = CommonUtils.rand(100 - forCorrect - forAns1);

            int forAns3 = 100 - forAns2 - forAns1 - forCorrect;
        
            List<Integer> list = new ArrayList<>();
            list.add(forAns1);
            list.add(forAns2);
            list.add(forAns3);
            
            int count = 1;
            while(count <= 4)
            {
               if(answer.equals(count++))
               {
                   toReturn += (forCorrect + " ");
               }
               else
               {
                   toReturn += (list.remove(0) + " ");
               }
            }
            
            askAudience = false;
        }
        
        return toReturn;
    }

    public String produceAskFriend(int questionNoumber)
    {
        String toReturn = "";
        
        if (askFriend && usedQuestions.add(questionNoumber))
        {
            Integer answer = correctAnswers.get(questionNoumber - 1);
            
            int threshold = 0;
            int rand = CommonUtils.rand(3);
          
            if(rand == 1)
            {
                threshold = 75;
            }
            else if(rand == 2)
            {
                threshold = 80;
            }
            else
            {
                threshold = 90;
            }
            
            if(threshold > CommonUtils.rand(100))
            {
                toReturn += answer;
            }
            else
            {
                boolean cont = true;
                while (cont)
                {
                    rand = CommonUtils.rand(4);
                    if (!answer.equals(rand))
                    {
                        toReturn += rand;
                        cont = false;
                    }

                }
            }

            askFriend = false;
        }
        
        return toReturn;
    }

}
