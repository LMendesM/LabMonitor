package model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String nome;
    private String mac;
    private String ipv4;
    private String ipv6;
    private int nucleosCpu;
    private long memoriaTotal;
    private long memoriaDisponivel;
    private Timestamp ultimoLogin;
    private String sistemaOperacional;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public int getNucleosCpu() {
        return nucleosCpu;
    }

    public void setNucleosCpu(int nucleosCpu) {
        this.nucleosCpu = nucleosCpu;
    }

    public long getMemoriaTotal() {
        return memoriaTotal;
    }

    public void setMemoriaTotal(long memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    public long getMemoriaDisponivel() {
        return memoriaDisponivel;
    }

    public void setMemoriaDisponivel(long memoriaDisponivel) {
        this.memoriaDisponivel = memoriaDisponivel;
    }

    public Timestamp getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Timestamp ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nome != null ? nome.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estacao)) {
            return false;
        }
        Estacao other = (Estacao) object;
        return !((this.nome == null && other.nome != null) || (this.nome != null && !this.nome.equals(other.nome)));
    }

    @Override
    public String toString() {
        return "model.Estacao[ id=" + nome + " ]";
    }

}
