package User;

public class Need {

    private String needId;//图书id
    private String userName;//用户名
    private String tel;//联系电话
    private String needThing;//需求物品
    private String grade;//图书是否被借, 是为1, 默认为0

    public String getNeedId() {
        return needId;
    }

    public void setNeedId(String needId) {
        this.needId = needId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String needName) {
        this.userName = needName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNeedThing() {
        return needThing;
    }

    public void setNeedThing(String needThing) {
        this.needThing = needThing;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
