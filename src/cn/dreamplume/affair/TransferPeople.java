package cn.dreamplume.affair;

/**
 * @Classname TransferPeople
 * @Description TODO
 * @Date 2021/2/16 8:49
 * @Created by 翊
 */
public class TransferPeople {
    private String userName;
    private int balance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TransferPeople{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
