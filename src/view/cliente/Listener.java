package view.cliente;

import controller.InstrucaoJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import model.Estacao;
import model.Instrucao;
import persistence.PersistenceSingleton;

public class Listener implements Runnable {

    Estacao estacao;

    Listener(Estacao estacao) {
        this.estacao = estacao;
    }

    @Override
    public void run() {
        while (true) {
            try {
                EntityManagerFactory emf = PersistenceSingleton.getInstance().getEntityManagerFactory();
                InstrucaoJpaController ijc = new InstrucaoJpaController(emf);
                List<Instrucao> listaInstrucao = ijc.findInstrucaoEntitiesByEstacao(estacao);

                for (Instrucao instrucao : listaInstrucao) {
                    Runtime.getRuntime().exec(instrucao.getComando());
                    instrucao.setAck(1);
                    ijc.edit(instrucao);
                }

                Thread.sleep(2 * 1000);
            } catch (Exception e) {
                System.err.println("Não foi possível executar o listener.\n" + e);
                JOptionPane.showMessageDialog(null, "Não foi possível executar o listener.\n" + e, "Erro interno do LabMontor", ERROR_MESSAGE);
            }

        }
    }
}
