package com.company;

/**
 * item類含火箭要携帶的物品及其重量
 * @param weight 要携帶的物品其重量
 * @param name 要携帶的物品名
 */
public class Item{
    //field
    private int weight ;
    private String name;
    //構造函數
    public Item(String name, int weight){
        this.name=name;
        this.weight=weight;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
