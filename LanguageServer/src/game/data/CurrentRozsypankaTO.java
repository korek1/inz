package game.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CurrentRozsypankaTO {

    private List<Map<Integer, String>> dataToStudent = new ArrayList<>();
    private List<Integer> dataFromStudent = new ArrayList<>();

    public CurrentRozsypankaTO()
    {
        super();
    }

    public void setData(List<Map<Integer, String>> processRozsypanka)
    {
        for (Map<Integer, String> map : processRozsypanka)
        {
            List<Integer> keys = new ArrayList<Integer>(map.keySet());
            Collections.shuffle(keys);
            Map<Integer, String> temp = new LinkedHashMap<>();
            for (Integer integer : keys)
            {
                String string = map.get(integer);
                temp.put(integer, string);
            }
            dataToStudent.add(temp);
        }
    }

    public List<Map<Integer, String>> getDataToStudent()
    {
        return dataToStudent;
    }

    public void setDataToStudent(List<Map<Integer, String>> dataToStudent)
    {
        this.dataToStudent = dataToStudent;
    }

    public List<Integer> getDataFromStudent()
    {
        return dataFromStudent;
    }

    public void setDataFromStudent(List<Integer> dataFromStudent)
    {
        this.dataFromStudent = dataFromStudent;
    }

}
