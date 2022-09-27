package com.umutcanmert;

import java.util.Objects;
//Bir array oluştuğunda exception hatası almaması için ilgili sınıf implement edildi ve metot yazildi.
public class RasyonelSayi implements Comparable<RasyonelSayi> {
    private int pay;
    private int payda;
    private static int yeniPay = 0;
    private static int yeniPayda = 0;
    private static RasyonelSayi rasyonelSayiYeni;

    public RasyonelSayi() {
        pay = 0;
        payda = 1;
    }

    //Ebob bulup ondan sonra sayinin en sade halini yazar.
    public RasyonelSayi(int pay, int payda) {
        int ebob = ebobBul(pay, payda);
        this.pay = pay / ebob;
        this.payda = payda / ebob;
    }

    //sayilari mutlak yapip sonra ebob bulur.
    private int ebobBul(int pay, int payda) {
        int payMutlak = Math.abs(pay);
        int paydaMutlak = Math.abs(payda);
        int ebob = 1;

        for (int i = 1; i <= payMutlak && i <= paydaMutlak; i++) {
            if (payMutlak % i == 0 && paydaMutlak % i == 0) {
                ebob = i;
            }
        }
        return ebob;
    }
    //Bu sınıftan iki nesneyi karsilastirildiginde dogru sonuc vermesi için equls ve hashcode eklendi.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RasyonelSayi)) return false;
        RasyonelSayi that = (RasyonelSayi) o;
        return pay == that.pay && payda == that.payda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pay, payda);
    }

    public final RasyonelSayi toplamBul(RasyonelSayi toplam) {
        yeniPay = (this.pay * toplam.payda) + (this.payda * toplam.pay);
        yeniPayda = (this.payda * toplam.payda);

        rasyonelSayiYeni = new RasyonelSayi(yeniPay, yeniPayda);

        return rasyonelSayiYeni;

    }

    public final RasyonelSayi carpmaBul(RasyonelSayi carpim) {
        yeniPay = this.pay * carpim.pay;
        yeniPayda = this.payda * carpim.payda;

        rasyonelSayiYeni = new RasyonelSayi(yeniPay, yeniPayda);

        return rasyonelSayiYeni;
    }

    public final RasyonelSayi bolmeBul(RasyonelSayi bolme) {
        yeniPay = this.pay * bolme.payda;
        yeniPayda = this.payda * bolme.pay;

        rasyonelSayiYeni = new RasyonelSayi(yeniPay, yeniPayda);

        return rasyonelSayiYeni;
    }

    public final RasyonelSayi cikarmaBul(RasyonelSayi cikarma) {
        yeniPay = (this.pay * cikarma.payda) - (this.payda * cikarma.pay);
        yeniPayda = (this.payda * cikarma.payda);


        rasyonelSayiYeni = new RasyonelSayi(yeniPay, yeniPayda);

        return rasyonelSayiYeni;
    }

    @Override
    public String toString() {
        if (payda == 1) {
            return pay + "";
        } else {
            return pay + "/" + payda;
        }
    }


    @Override
    public int compareTo(RasyonelSayi o) {
        if (this.cikarmaBul(o).pay > 0) {
            return 1;
        } else if (this.cikarmaBul(o).pay < 0) {
            return -1;
        } else return 0;
    }
}
