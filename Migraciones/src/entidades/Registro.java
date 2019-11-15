/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

/**
 *
 * @author nicoleagila
 */
public class Registro {
    private Migrante migrante;
    private TipoMov tip_movi;
    private String via_transp;
    private String prov_jefm;
    private String can_jefm;
    private Date fecha_mov;
    private String ocu_migr;
    private String mot_viam;
    private String nac_migr;
    private String pais_prod;
    private String lug_pro;
    private String cont_prod;
    private String cont_res;
    private String cont_nac;
    private String subcont_prod;
    private String subcont_nac;

    public Migrante getMigrante() {
        return migrante;
    }

    public TipoMov getTip_movi() {
        return tip_movi;
    }

    public String getVia_transp() {
        return via_transp;
    }

    public String getProv_jefm() {
        return prov_jefm;
    }

    public String getCan_jefm() {
        return can_jefm;
    }

    public Date getFecha_mov() {
        return fecha_mov;
    }

    public String getOcu_Migr() {
        return ocu_migr;
    }

    public String getMot_viam() {
        return mot_viam;
    }

    public String getNac_migr() {
        return nac_migr;
    }

    public String getPais_prod() {
        return pais_prod;
    }

    public String getLug_pro() {
        return lug_pro;
    }

    public String getCont_prod() {
        return cont_prod;
    }

    public String getCont_res() {
        return cont_res;
    }

    public String getCont_nac() {
        return cont_nac;
    }

    public String getSubcont_prod() {
        return subcont_prod;
    }

    public String getSubcont_nac() {
        return subcont_nac;
    }

    public void setMigrante(Migrante migrante) {
        this.migrante = migrante;
    }

    public void setTip_movi(TipoMov tip_movi) {
        this.tip_movi = tip_movi;
    }

    public void setVia_transp(String via_transp) {
        this.via_transp = via_transp;
    }

    public void setProv_jefm(String prov_jefm) {
        this.prov_jefm = prov_jefm;
    }

    public void setCan_jefm(String can_jefm) {
        this.can_jefm = can_jefm;
    }

    public void setFecha_mov(Date fecha_mov) {
        this.fecha_mov = fecha_mov;
    }

    public void setOcu_migr(String ocu_migr) {
        this.ocu_migr = ocu_migr;
    }

    public void setMot_viam(String mot_viam) {
        this.mot_viam = mot_viam;
    }

    public void setNac_migr(String nac_migr) {
        this.nac_migr = nac_migr;
    }

    public void setPais_prod(String pais_prod) {
        this.pais_prod = pais_prod;
    }

    public void setLug_pro(String lug_pro) {
        this.lug_pro = lug_pro;
    }

    public void setCont_prod(String cont_prod) {
        this.cont_prod = cont_prod;
    }

    public void setCont_res(String cont_res) {
        this.cont_res = cont_res;
    }

    public void setCont_nac(String cont_nac) {
        this.cont_nac = cont_nac;
    }

    public void setSubcont_prod(String subcont_prod) {
        this.subcont_prod = subcont_prod;
    }

    public void setSubcont_nac(String subcont_nac) {
        this.subcont_nac = subcont_nac;
    }

    public Registro(Migrante migrante, TipoMov tip_movi, String via_transp, String prov_jefm, String can_jefm, Date fecha_mov, String ocu_migr, String mot_viam, String nac_migr, String pais_prod, String lug_pro, String cont_prod, String cont_res, String cont_nac, String subcont_prod, String subcont_nac) {
        this.migrante = migrante;
        this.tip_movi = tip_movi;
        this.via_transp = via_transp;
        this.prov_jefm = prov_jefm;
        this.can_jefm = can_jefm;
        this.fecha_mov = fecha_mov;
        this.ocu_migr = ocu_migr;
        this.mot_viam = mot_viam;
        this.nac_migr = nac_migr;
        this.pais_prod = pais_prod;
        this.lug_pro = lug_pro;
        this.cont_prod = cont_prod;
        this.cont_res = cont_res;
        this.cont_nac = cont_nac;
        this.subcont_prod = subcont_prod;
        this.subcont_nac = subcont_nac;
    }
    
}
