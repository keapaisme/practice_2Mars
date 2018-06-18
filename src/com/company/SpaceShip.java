package com.company;

/**
 * SpaceShip 接口
 */
public interface SpaceShip {
    boolean launch(int temp);
    boolean land(int temp);
    boolean canCarry(Item item);
    int carry(Item item);
}
