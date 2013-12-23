package auth;

import utils.CommonUtils;

public class TempPass {

    private static final int TIME_VALID = 1000 * 60 * 15; // 15 min

    private long timeStamp;
    private String tempPass;

    public TempPass(String tempPass)
    {
        super();
        this.tempPass = tempPass;
        this.timeStamp = CommonUtils.getCurrentTimeStamp();
    }

    public void updateTimeStamp()
    {
        this.timeStamp = CommonUtils.getCurrentTimeStamp();
    }

    public boolean isValid()
    {
        return (CommonUtils.getCurrentTimeStamp() - timeStamp) < TIME_VALID ? true : false;
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getTempPass()
    {
        return tempPass;
    }

    public void setTempPass(String tempPass)
    {
        this.tempPass = tempPass;
    }

}
