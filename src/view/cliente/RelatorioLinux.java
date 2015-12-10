package view.cliente;

import controller.EstacaoJpaController;
import controller.ProcessoJpaController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import model.Estacao;
import model.Processo;
import persistence.PersistenceSingleton;
import util.DadosEstacao;

public class RelatorioLinux implements Runnable {

    Estacao estacao = new Estacao();

    public void setEstacaoEntity() {
        try {
            estacao.setIpv4(DadosEstacao.getEnderecoIPv4());
            estacao.setIpv6(DadosEstacao.getEnderecoIPv6());
            estacao.setNome(DadosEstacao.getNomeMaquina());
            estacao.setMac(DadosEstacao.getEnderecoMac());
            estacao.setNucleosCpu(DadosEstacao.getNucleosCpu());
            estacao.setMemoriaTotal(DadosEstacao.getMemoriaTotal());
            estacao.setMemoriaDisponivel(DadosEstacao.getMemoriaDisponivel());
            estacao.setUltimoLogin(new Timestamp(System.currentTimeMillis()));
            estacao.setSistemaOperacional(System.getProperty("os.name"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pegar atributos da estação", "Erro interno do LabMonitor", ERROR_MESSAGE);
        }
    }

    public Estacao getEstacaoEntity() {
        return estacao;
    }

    @Override
    public void run() {
        if (System.getProperty("os.name").equals("Linux")) {
            EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();

            EstacaoJpaController ejc = new EstacaoJpaController(emf);
            ProcessoJpaController pjc = new ProcessoJpaController(emf);

            Processo processo = new Processo();

            try {
                setEstacaoEntity();
                ejc.edit(estacao);
                while (true) {
                    List<Processo> listaProcessos;
                    listaProcessos = pjc.findProcessoEntities();

                    for (Processo proc : listaProcessos) {
                        proc.setStatus(0);
                        pjc.edit(proc);
                    }

                    InputStreamReader isr = new InputStreamReader(Runtime.getRuntime().exec("ps -U alunos").getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String line = "";
                    boolean primeiroInt;

                    while ((line = br.readLine()) != null) {
                        String pid;
                        String cmd;
                        primeiroInt = false;
                        pid = "";

                        if (!line.contains("PID")) {
                            cmd = line.substring(24, line.length());

                            for (int i = 0; i < line.length(); i++) {
                                if (line.charAt(i) != ' ') {
                                    primeiroInt = true;
                                    pid += line.charAt(i);
                                }
                                if (primeiroInt) {
                                    if (line.charAt(i) == ' ') {
                                        break;
                                    }
                                }
                            }

                            processo.setPid(Integer.parseInt(pid));
                            processo.setCmd(cmd);
                            processo.setEstacao(estacao);
                            processo.setStatus(1);

                            pjc.edit(processo);
                        }
                    }

                    Thread.sleep(2 * 1000);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "Erro interno do LabMonitor", ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Classe compatível somente com Linux", "Erro interno do LabMonitor", ERROR_MESSAGE);
        }
    }

}
