package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Lab3ChatRoomClient_V0 {
    static private String username="xiaoxue";

    public static void main(String[] args) throws IOException {
        try {
            Socket sock = new Socket("127.0.0.1",5656);

            // reader is the input stream from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));


            // client connecting

            System.out.print("please input username: ");
            username = stdIn.readLine();
            writer.println(username);
            writer.println("clientWriteDone");
            writer.flush();

            //user logined and start chat
            String serverline;
                while ( (serverline = reader.readLine()) != null){
                   // System.out.println("client debug:"+serverline);
                   if(serverline.equalsIgnoreCase("serverWriteDone")){
                       System.out.print("\nyou: ");

                        String words;
                        if ((words = stdIn.readLine()) != null) {
                            writer.println(words);
                            writer.println("clientWriteDone");
                            writer.flush();
//                          if (words.equalsIgnoreCase("exit")||sc.nextLine().equalsIgnoreCase("quit")){
//                            sock.close();
//                            break;
//                          }
                        }
                   }else {
                       System.out.println(serverline);
                   }

                }







        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}