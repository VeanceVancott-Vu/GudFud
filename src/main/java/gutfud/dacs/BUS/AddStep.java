package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AddStep {
    private ArrayList<String> StepList;
    private ArrayList<String> OrderList;
    private ArrayList<Step> stepsList;
    private DataStreamManager manager;
    public AddStep(ArrayList<String> stepList , ArrayList<String> orderList)
    {
        this.StepList = stepList;
        this.OrderList = orderList;
    }
    public AddStep(ArrayList<Step> stepsList, DataStreamManager manager)
    {
        this.stepsList  = stepsList;
        this.manager=manager;
    }
    public void AddStep() throws IOException {
        for(Step step : stepsList)
        {
            manager.sendAction("Add-step");
            manager.sendStep(step);
        }
    }

}
