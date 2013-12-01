package game.helpers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IntGenerator {

    private Set<Integer> set = new HashSet<>();
    private Random rand = new Random();

    public Integer getNextRandomUniqueInt()
    {
        Integer i = null;

        boolean added = false;
        while (!added)
        {
            i = rand.nextInt(10000);
            added = set.add(i);
        }

        return i;

    }
}
