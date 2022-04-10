package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {


    private MServer server;
    private Socket socket;
    private DataInputStream  in;
    private DataOutputStream out;
    private String nickname;

    public ClientHandler(MServer server, Socket socket)
    {
        try{
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());


            new Thread(()-> {


                try
                {
                    while(true)
                    {

                        String str = in.readUTF();
                        String[] tokens = str.split("\\s");

                        if(tokens[0].equals("/auth"))
                        {
                            String nickFromDB = SQLHandler.getNickByLogPass(tokens[1], tokens[2]);

                            if(nickFromDB != null)
                            {
                                sendMsg("/authok");
                                server.add_client(this);
                                break;
                            }
                        }
                    }
                    while(true)
                    {
                        String str = in.readUTF();

                        System.out.println("A message from a client: " + str);

                        if(str.equalsIgnoreCase("/end"))
                        {
                            break;
                        }

                        server.broadcastMsg(str);

                        out.flush();
                    }
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    try
                    {
                        in.close();


                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    try
                    {
                        out.close();
                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }
                    try
                    {
                        socket.close();
                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace();
                    }

                    server.remove_client(this);

                }

            }).start();

        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }


    public void sendMsg(String msg)
    {
        try
        {
            out.writeUTF(msg);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}