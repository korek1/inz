package game.to;

import java.util.ArrayList;
import java.util.List;

public class SolutionTO {

    private List<Integer> dataFromStudent = new ArrayList<>();

    public SolutionTO()
    {
        super();
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
