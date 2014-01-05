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

    private FiftyFiftyHelper fiftyFiftyHelper;

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

        if (fiftyFifty)
        {
            fiftyFiftyHelper = new FiftyFiftyHelper(questionNoumber);

            Integer answer = correctAnswers.get(questionNoumber - 1);

            int count = 0;
            while (count != 2)
            {
                int rand = CommonUtils.rand(4);
                if (!answer.equals(rand))
                {
                    boolean addRejectedAns = fiftyFiftyHelper.addRejectedAns(rand);
                    if (addRejectedAns)
                    {
                        toReturn += (rand + " ");
                        count++;
                    }
                }

            }

            fiftyFifty = false;
        }

        return toReturn;
    }

    public String produceAskAudience(int questionNoumber)
    {
        String toReturn = "";

        if (askAudience)
        {
            Integer answer = correctAnswers.get(questionNoumber - 1);

            int threshold = 0;
            int rand = CommonUtils.rand(3);

            if (rand == 1)
            {
                threshold = 25;
            }
            else
                if (rand == 2)
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

            list.add(answer - 1, forCorrect);

            if (CommonUtils.isNotNull(fiftyFiftyHelper) && fiftyFiftyHelper.usedForThisQuestion(questionNoumber))
            {
                Integer rejectedAns1 = fiftyFiftyHelper.getRejectedAns1();
                Integer rejectedAns2 = fiftyFiftyHelper.getRejectedAns2();

                int leftIndex = 0;
                Set<Integer> set = new HashSet<>();
                set.add(answer);
                set.add(rejectedAns1);
                set.add(rejectedAns2);
                for (int i = 1; i < 5; i++)
                {
                    if (!set.contains(i))
                    {
                        leftIndex = i - 1;
                        break;
                    }
                }

                Integer valueLeft = list.get(leftIndex);

                forCorrect += list.set(rejectedAns1 - 1, 0);
                valueLeft += list.set(rejectedAns2 - 1, 0);

                list.set(leftIndex, valueLeft);
                list.set(answer - 1, forCorrect);
            }

            for (Integer integer : list)
            {
                toReturn += (integer + " ");
            }

            askAudience = false;
        }

        return toReturn;
    }

    public String produceAskFriend(int questionNoumber)
    {
        String toReturn = "";

        if (askFriend)
        {
            Integer answer = correctAnswers.get(questionNoumber - 1);

            int threshold = 0;
            int rand = CommonUtils.rand(3);

            if (rand == 1)
            {
                threshold = 75;
            }
            else
                if (rand == 2)
                {
                    threshold = 80;
                }
                else
                {
                    threshold = 90;
                }

            if (threshold > CommonUtils.rand(100))
            {
                toReturn += answer;
            }
            else
            {
                if (CommonUtils.isNotNull(fiftyFiftyHelper) && fiftyFiftyHelper.usedForThisQuestion(questionNoumber))
                {
                    Set<Integer> set = new HashSet<>();
                    set.add(answer);
                    set.add(fiftyFiftyHelper.getRejectedAns1());
                    set.add(fiftyFiftyHelper.getRejectedAns2());
                    for (int i = 1; i < 5; i++)
                    {
                        if (!set.contains(i))
                        {
                            toReturn += i;
                            break;
                        }
                    }
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
            }

            askFriend = false;
        }

        return toReturn;
    }

}
