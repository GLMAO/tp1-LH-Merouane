package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge ;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import java.util.Random;

import org.emp.gl.clients.CompteARebours;


/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
 
        TimerService timerService = new DummyTimeServiceImpl();

        Horloge h1 = new Horloge("Horloge-1", timerService);

        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int val = 7 + random.nextInt(5);
            new CompteARebours("Cpt-" + i, val, timerService);
        }
    }
      
    

}
