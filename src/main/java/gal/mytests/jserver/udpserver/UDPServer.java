package gal.mytests.jserver.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

    public static void main(final String[] args) {
        if (args.length != 1) {
            System.err.println("Formato: ServidorUDP <puerto>");
            System.exit(-1);
        }

        final int puerto = Integer.parseInt(args[0]);
        try (DatagramSocket socket = new DatagramSocket(puerto)) {

            // Establecemos un timeout de 30 segs
            socket.setSoTimeout(30000);

            while (true) {
                final byte array[] = new byte[1024];
                final DatagramPacket paketeRec = new DatagramPacket(array, array.length);
                socket.receive(paketeRec);

                final DatagramPacket paketeEnv = new DatagramPacket(paketeRec.getData(), paketeRec.getLength(),
                        paketeRec.getAddress(), paketeRec.getPort());
                socket.send(paketeEnv);

                System.out.println("CLIENTE: Enviando ");
            }

        } catch (final IOException e) {
            throw new InternalError(e);
        }
    }

}
