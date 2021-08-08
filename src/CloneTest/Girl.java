package CloneTest;

import java.io.Serializable;

public class Girl implements Serializable {
    private int legNum;
    public Girl(int legNum){
        this.legNum = legNum;
    }

    public int getLegNum() {
        return legNum;
    }

    public void setLegNum(int legNum) {
        this.legNum = legNum;
    }
}
