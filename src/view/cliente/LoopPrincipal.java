package view.cliente;

public class LoopPrincipal {
    public static void main(String[] args){
        RelatorioLinux relatorioLinux = new RelatorioLinux();
        Thread threadRelatorioLinux = new Thread(relatorioLinux);
        threadRelatorioLinux.start();
        
        Listener listener = new Listener(relatorioLinux.getEstacaoEntity());
        Thread threadListener = new Thread(listener);
        threadListener.start();
    }
}
