package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Processo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int pid;
    @Id
    private String cmd;
    private int status;
    @ManyToOne
    private Estacao estacao;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmd != null ? cmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Processo)) {
            return false;
        }
        Processo other = (Processo) object;
        return !((this.cmd == null && other.cmd != null) || (this.cmd != null && !this.cmd.equals(other.cmd)));
    }

    @Override
    public String toString() {
        return "model.Processo[ id=" + cmd + " ]";
    }
    
}
