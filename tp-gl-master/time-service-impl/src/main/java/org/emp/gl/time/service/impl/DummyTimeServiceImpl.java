/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import java.beans.PropertyChangeSupport;


/**
 *
 * @author tina
 */
public class DummyTimeServiceImpl
        implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    List<TimerChangeListener> listeners = new LinkedList<>();
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Constructeur du DummyTimeServiceImpl: ici, 
     * nous nous avons utilisé un objet Timer, qui permet de
     * réaliser des tics à chaque N millisecondes
     */
    public DummyTimeServiceImpl() {
        setTimeValues();
        // initialize schedular
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
             @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();

        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

   


    @Override
    public void addTimeChangeListener(TimerChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }


    private void timeChanged() {
        setTimeValues();
    }

     public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde != newDixiemeDeSeconde) {
            int oldValue = dixiemeDeSeconde;
            dixiemeDeSeconde = newDixiemeDeSeconde;
            pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldValue, newDixiemeDeSeconde);
        }
    }

    public void setSecondes(int newSecondes) {
        if (secondes != newSecondes) {
            int oldValue = secondes;
            secondes = newSecondes;
            pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, oldValue, newSecondes);
        }
    }

    public void setMinutes(int newMinutes) {
        if (minutes != newMinutes) {
            int oldValue = minutes;
            minutes = newMinutes;
            pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, oldValue, newMinutes);
        }
    }

    public void setHeures(int newHeures) {
        if (heures != newHeures) {
            int oldValue = heures;
            heures = newHeures;
            pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, oldValue, newHeures);
        }
    }


    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}