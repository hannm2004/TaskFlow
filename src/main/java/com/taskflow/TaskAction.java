/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taskflow;

/**
 *
 * @author HP
 */
@FunctionalInterface
public interface TaskAction {

    void execute(Task task);
}
