/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19_simulationn;

/**
 *
 * @author abdurrahman_sefer
 */
public class person {
    
        int hasta_durumu=0;
    
    int px=0;
    int py=0;
    int son_duruma_gecen_zaman=0;
    int test_durumu=0;

    public int getTest_durumu() {
        return test_durumu;
    }

    public void setTest_durumu(int test_durumu) {
        this.test_durumu = test_durumu;
    }
    
    
    
    public int getHasta_durumu() {
        return hasta_durumu;
    }

    public void setHasta_durumu(int hasta_durumu) {
        this.hasta_durumu = hasta_durumu;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getSon_duruma_gecen_zaman() {
        return son_duruma_gecen_zaman;
    }

    //0=normal, 1=hasta ,2=enfekte oldu
    public void setSon_duruma_gecen_zaman(int son_duruma_gecen_zaman) {
        this.son_duruma_gecen_zaman = son_duruma_gecen_zaman;
    }

    
}
