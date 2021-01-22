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
public class Histogram_verileri {

    private int enfekte_olmamis_kisiler = 0;
    private int pozitif_kisiler = 0;
    private int iyilesen_kisiler = 0;

    public int getToplam_kislerin_sayisi() {
        return toplam_kislerin_sayisi;
    }

    public void setToplam_kislerin_sayisi(int toplam_kislerin_sayisi) {
        this.toplam_kislerin_sayisi = toplam_kislerin_sayisi;
    }
    private int toplam_kislerin_sayisi = 0;

    public int getEnfekte_olmamis_kisiler() {
        return enfekte_olmamis_kisiler;
    }

    public void setEnfekte_olmamis_kisiler(int enfekte_olmamis_kisiler) {
        this.enfekte_olmamis_kisiler = enfekte_olmamis_kisiler;
    }

    public int getPozitif_kisiler() {
        return pozitif_kisiler;
    }

    public void setPozitif_kisiler(int pozitif_kisiler) {
        this.pozitif_kisiler = pozitif_kisiler;
    }

    public int getIyilesen_kisiler() {
        return iyilesen_kisiler;
    }

    public void setIyilesen_kisiler(int iyilesen_kisiler) {
        this.iyilesen_kisiler = iyilesen_kisiler;
    }

}
