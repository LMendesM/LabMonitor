package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class DadosEstacao {

    public static int getNucleosCpu() {
        return (Runtime.getRuntime().availableProcessors());
    }

    public static String getEnderecoIPv4() throws UnknownHostException {
        String ipv4 = "IPv4 não encontrado";
        try {
            NetworkInterface ni = NetworkInterface.getByName("eth0");
            Enumeration<InetAddress> eia = ni.getInetAddresses();
            while (eia.hasMoreElements()) {
                InetAddress ia = eia.nextElement();
                if (ia instanceof Inet4Address) {
                    return ia.getHostAddress();
                }
            }
            return ipv4;
        } catch (Exception e) {
            System.out.println("Erro ao capturar endereço IPv4. " + e);
            return ipv4;
        }
    }

    public static String getEnderecoIPv6() {
        String ipv6 = "IPv6 não encontrado";
        try {
            NetworkInterface ni = NetworkInterface.getByName("eth0");
            Enumeration<InetAddress> eia = ni.getInetAddresses();
            while (eia.hasMoreElements()) {
                InetAddress ia = eia.nextElement();
                if (ia instanceof Inet6Address) {
                    ipv6 = ia.getHostAddress().substring(0, ia.getHostAddress().length() - 5);
                }
            }
            return ipv6;
        } catch (Exception e) {
            System.out.println("Erro ao capturar endereço IPv6. " + e);
            return ipv6;
        }
    }

    public static String getNomeMaquina() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public static long getMemoriaTotal() {
        com.sun.management.OperatingSystemMXBean mxbean;
        mxbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return mxbean.getTotalPhysicalMemorySize();
    }

    public static long getMemoriaDisponivel() {
        com.sun.management.OperatingSystemMXBean mxbean;
        mxbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return mxbean.getFreePhysicalMemorySize();
    }

    public static String getEnderecoMac() throws SocketException {
        NetworkInterface ni = NetworkInterface.getByName("eth0");
        String address = "";
        byte[] mac = ni.getHardwareAddress();
        for (int i = 0; i < mac.length; i++) {
            address += (String.format("%02X:", mac[i]));
        }
        return address.substring(0, address.length() - 1);
    }
}
