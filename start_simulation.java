/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_simulationn;

import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abdurrahman_sefer
 */
public class start_simulation {

    panel panel_ayarlari;
    ArrayList<person> Liste = new ArrayList<person>();
    Random rnd = new Random();

    int pan_x = 0;
    int pan_y = 0;
    simulation_pan panel;

    jframe jframe_;

    static boolean exit = false;

  

    Histogram_verileri histogram_verileri;

    public start_simulation( jframe jframe_) {
        this.panel_ayarlari = panel_ayarlari;
        this.panel = (simulation_pan)jframe_.getpan_sim();;
        pan_x = panel.getWidth();
        pan_y = panel.getHeight();
        this.jframe_ = jframe_;
        histogram_verileri = new Histogram_verileri();

    }

  

   
  
    simulation_pan getsimulation_pan() {
        return (simulation_pan) jframe_.getpan_sim();
    }

    public void start_simulation() {
        exit = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
             
            getsimulation_pan().repaint();
                while (!exit) {
                  
                    try {
                        Thread.sleep(1000);
                        sayac++;
                       

                    } catch (InterruptedException ex) {
                        Logger.getLogger(start_simulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        ).start();;

    }

    public void stop_simulation() {
        exit = true;
    }



}
