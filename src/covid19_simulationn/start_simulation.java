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

    int gun_sayisi = 0;
    int sayac = 0;

    Histogram_verileri histogram_verileri;

    public start_simulation(panel panel_ayarlari, jframe jframe_) {
        this.panel_ayarlari = panel_ayarlari;
        this.panel = (simulation_pan) jframe_.getpan_sim();;
        pan_x = panel.getWidth();
        pan_y = panel.getHeight();
        this.jframe_ = jframe_;
        histogram_verileri = new Histogram_verileri();

    }

    void liste_olustur() {
        histogram_verileri.setEnfekte_olmamis_kisiler(panel_ayarlari.getPopulasiyon());
        int x = panel.getWidth() / (panel_ayarlari.getPopulasiyon() / panel_ayarlari.getPopulasiyon());
        int y = panel.getHeight() / (panel_ayarlari.getPopulasiyon() / panel_ayarlari.getPopulasiyon());
        int person_new_x = 0;
        int person_new_y = 0;

        int onceki_x = 0;
        int onceki_y = 0;

        for (int i = 1; i <= panel_ayarlari.getPopulasiyon(); i++) {
            person_new_x = rnd.nextInt(panel_ayarlari.getMobilite_hiz() + x) + onceki_x;
            person_new_y = rnd.nextInt(panel_ayarlari.getMobilite_hiz() + y) + onceki_y;
            if (person_new_x >= 700) {

                person_new_x = rnd.nextInt(panel_ayarlari.getMobilite_hiz() + x);

                person_new_y = person_new_y + onceki_y;
            }

            person new_person = new person();

            new_person.setPx(person_new_x);
            new_person.setPy(person_new_y);
            new_person.setSon_duruma_gecen_zaman(0);

            onceki_x = person_new_x;

            Liste.add(new_person);
        }
        hastalari_yerlestir();
    }

    void hastalari_yerlestir() {
        int hasta_sayisi = panel_ayarlari.getHasta_sayisi();
        int i = 0;
        while (hasta_sayisi > i) {
            int yer = rnd.nextInt(panel_ayarlari.getPopulasiyon());
            if (Liste.get(yer).getHasta_durumu() == 0) {
                Liste.get(yer).setHasta_durumu(1);
                i++;
                histogram_verileri.setEnfekte_olmamis_kisiler(histogram_verileri.getEnfekte_olmamis_kisiler() - 1);
                histogram_verileri.setPozitif_kisiler(histogram_verileri.getPozitif_kisiler() + 1);

            }

        }

    }

    void draw_person() {
        
        getsimulation_pan().drawe_person(Liste, panel_ayarlari);
        getHistogramPanel().draw_histogram(histogram_verileri);
    }

    simulation_pan getsimulation_pan() {
        return (simulation_pan) jframe_.getpan_sim();
    }

    HistogramPanel getHistogramPanel() {

        return (HistogramPanel) jframe_.gethistogram_pan();

    }

    public void start_simulation() {
        exit = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                liste_olustur();
                getHistogramPanel().draw_histogram(histogram_verileri);
                while (!exit) {
                    draw_person();
                    try {
                        Thread.sleep(1000);
                        sayac++;

                        if (sayac % panel_ayarlari.getSimulasiyon_speed() == 0) {
                            gun_sayisi++;
                            jframe_.setpassing_day_lbl(String.valueOf(gun_sayisi));
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(start_simulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        liste_guncele();
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

    int generatRandomPositiveNegitiveValue(int max, int min) {
        Random random = new Random();
        random.setSeed(random.nextLong());
        int number = random.nextInt(max - min) + min;
        return number;
    }

    void liste_guncele() throws InterruptedException {
        int person_new_x = 0;
        int person_new_y = 0;
        int onceki_x = 0;
        int onceki_y = 0;
        for (int i = 0; i < panel_ayarlari.getPopulasiyon(); i++) {

            int randx = generatRandomPositiveNegitiveValue(panel_ayarlari.getMobilite_hiz(), -panel_ayarlari.getMobilite_hiz());
            int randy = generatRandomPositiveNegitiveValue(panel_ayarlari.getMobilite_hiz(), -panel_ayarlari.getMobilite_hiz());

            person_new_x = Liste.get(i).getPx() + randx;
            person_new_y = Liste.get(i).getPy() + randy;

            if (person_new_x >= 685) {

                person_new_x = person_new_x - panel_ayarlari.getMobilite_hiz();

            } else if (person_new_x < panel_ayarlari.getMobilite_hiz()) {

                person_new_x = person_new_x + panel_ayarlari.getMobilite_hiz();
            }
            if (person_new_y >= panel.getHeight() - panel_ayarlari.getMobilite_hiz()) {
                person_new_y = person_new_y - panel_ayarlari.getMobilite_hiz();

            } else if (person_new_y < panel_ayarlari.getMobilite_hiz()) {

                person_new_y = person_new_y + panel_ayarlari.getMobilite_hiz();
            }

            Liste.get(i).setPx(person_new_x);
            Liste.get(i).setPy(person_new_y);

            if (Liste.get(i).getHasta_durumu() == 1) {
                if (Liste.get(i).getSon_duruma_gecen_zaman() > panel_ayarlari.getIyilesme_suersi()) {
                    Liste.get(i).setHasta_durumu(2);
                    histogram_verileri.setIyilesen_kisiler(histogram_verileri.getIyilesen_kisiler() + 1);
                    histogram_verileri.setPozitif_kisiler(histogram_verileri.getPozitif_kisiler() - 1);

                } else {
                    if (sayac % panel_ayarlari.getSimulasiyon_speed() == 0) {
                        Liste.get(i).setSon_duruma_gecen_zaman(Liste.get(i).getSon_duruma_gecen_zaman() + 1);
                    }

                }

            }
            onceki_x = person_new_x;

        }
        bulasti_mi_kontrol();
    }

    void bulasti_mi_kontrol() {

        for (int i = 0; i < Liste.size(); i++) {
            Liste.get(i).setTest_durumu(0);
        }
        for (int i = 0; i < Liste.size(); i++) {
            if (Liste.get(i).getTest_durumu() == 0) {
                for (int j = i + 1; j < Liste.size(); j++) {
                    if ((Liste.get(i).getHasta_durumu() == 1 && Liste.get(j).getHasta_durumu() == 0)||(Liste.get(i).getHasta_durumu() == 0 && Liste.get(j).getHasta_durumu() == 1)) {
                        

                        if (Math.abs(Liste.get(i).getPx() - Liste.get(j).getPx()) < 10 && Math.abs(Liste.get(i).getPy() - Liste.get(j).getPy()) < 10) {
                            Liste.get(i).setHasta_durumu(1);
                            Liste.get(j).setHasta_durumu(1);
                            Liste.get(j).setTest_durumu(1);
                            histogram_verileri.setPozitif_kisiler(histogram_verileri.getPozitif_kisiler() + 1);
                            histogram_verileri.setEnfekte_olmamis_kisiler(histogram_verileri.getEnfekte_olmamis_kisiler() - 1);

                        }
                    }

                }

            }

        }

    }

    Histogram_verileri getHistogram_verileri() {
        return this.histogram_verileri;

    }

}
