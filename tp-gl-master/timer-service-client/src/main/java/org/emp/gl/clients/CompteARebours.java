package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private final String name;
    private int valeur;
    private final TimerService timerService;

    public CompteARebours(String name, int valeurInitiale, TimerService timerService) {
        this.name = name;
        this.valeur = valeurInitiale;
        this.timerService = timerService;

        timerService.addTimeChangeListener(this);
        System.out.println("Compte à rebours " + name + " démarré à " + valeur + " secondes");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(TimerChangeListener.SECONDE_PROP)) {
        tick();
        }
    }

    private void tick() {
        if (valeur > 0) {
            valeur--;
            System.out.println(name + " → " + valeur);

            if (valeur == 0) {
                System.out.println(name + " terminé !");
                timerService.removeTimeChangeListener(this); // se désinscrit
            }
        }
    }

}
