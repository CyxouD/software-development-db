package gui;

import db.DBOperations;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Dima on 02.05.2016.
 */
public class MainTasks {
    private DBOperations dbOperations;

    public MainTasks(){
        dbOperations = new DBOperations();
    }

    public void findIfCrossplatformingActual(Component component){
        int percentOfUsageOfTwoOrMoreOs = dbOperations.getPercentOfProgramThatUseTwoOrMoreOs();
        if (percentOfUsageOfTwoOrMoreOs > 20){
            Thread t = new Thread(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(component,
                            "Да, кроссплатформенность нужна для " + percentOfUsageOfTwoOrMoreOs + "% программ", "Положительный ответ",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            t.start();
        }
        else { // print what operating systems are the most popular
            Object[][] data = dbOperations.getOsFrequencyOfUse();
            if (data.length == 0){
                JOptionPane.showMessageDialog(component, "Список данных пуст. Скорее всего нет связей между операционной системой и программой");
            }
            String[] fields = {"Операционная система", "Количество использования"};
            new Dialog(data, fields);
        }
    }

    public void findPopularityOfEachEachLang(Component component){
        Object[][] data = dbOperations.getPopularityOfProgramLanguagesInProgram();
        if (data.length == 0){
            JOptionPane.showMessageDialog(component, "Список данных пуст. Скорее всего нет связей между языком программирования и программой");
        }
        String[] fields = {"Язык", "Общий бюджет проектов", "Наиболее частая ОС языка", "Количество программ"};
        new Dialog(data, fields);
    }

    public void findWhatMethodologySuccesfulProjectsUse(Component component){
        Object[][] data = dbOperations.getSuccessfulProjectMethodology();
        if (data.length == 0){
            JOptionPane.showMessageDialog(component, "Список данных пуст. Скорее всего нет связей между проектами и методологией программирования");
        }
        String[] fields = {"Проект", "Процентное соотношение выполненых требований", "Методология"};
        new Dialog(data, fields);
    }

    public void findWhatRequirementsAreDoneOnTime(Component component){
        Object[][] data = dbOperations.getTopRequirementOnTime();
        if (data.length == 0){
            JOptionPane.showMessageDialog(component, "Список данных пуст. Скорее всего нет связей между проектами и требованиями к проекту");
        }
        String[] fields = {"Требование в срок"};
        new Dialog(data, fields);
    }

    public void findWhatRequirementsAreDoneAtWrongTime(Component component){
        Object[][] data = dbOperations.getTopRequirementAtWrongTime();
        if (data.length == 0){
            JOptionPane.showMessageDialog(component, "Список данных пуст. Скорее всего нет связей между проектами и требованиями к проекту");
        }
        String[] fields = {"Требование не в срок"};
        new Dialog(data, fields);
    }

}
