package com.wk.learn.bean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/28 12:19
 */
public class PieData {

    // 用户关心数据
    private String name;        // 名字
    private float value;        // 数值
    private float percentage;   // 百分比

    // 非用户关心数据
    private int color = 0;      // 颜色
    private float angle = 0;    // 角度

    public PieData(String name, float value,int color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
