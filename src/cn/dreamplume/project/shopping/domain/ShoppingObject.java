package cn.dreamplume.project.shopping.domain;

/**
 * @Classname ShoppingObject
 * @Description TODO
 * @Date 2021/2/18 10:59
 * @Created by 翊
 */
public class ShoppingObject {
    private int id;
    private String name;
    private String type;
    private int stock;
    private int selling_price;
    private int total_sales;
    private int total_monthly_sales;
    private String introduce;

    @Override
    public String toString() {
        return "ShoppingObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                ", selling_price=" + selling_price +
                ", total_sales=" + total_sales +
                ", total_monthly_sales=" + total_monthly_sales +
                ", introduce='" + introduce + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }

    public int getTotal_monthly_sales() {
        return total_monthly_sales;
    }

    public void setTotal_monthly_sales(int total_monthly_sales) {
        this.total_monthly_sales = total_monthly_sales;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
