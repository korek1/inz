package dto.to;

import java.util.ArrayList;
import java.util.List;

public class OnlineTOs {

    private List<OnlineTO> onlines = new ArrayList<>();

    public OnlineTOs()
    {
        super();
    }

    public List<OnlineTO> getOnlines()
    {
        return onlines;
    }

    public void setOnlines(List<OnlineTO> onlines)
    {
        this.onlines = onlines;
    }

    public void addOnline(String login)
    {
        onlines.add(new OnlineTO(login, true));
    }

    public void addOffline(String login)
    {
        onlines.add(new OnlineTO(login, false));
    }

}
