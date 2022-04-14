package client;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static server.SQLHandler.connect;

public class FXMLDocumentController implements Initializable {

    private boolean authorized;
    private final static long AUTH_TIMEOUT = 120 * 1000;
    private boolean isAuth = false;

    public void setAthorized(boolean authorized)
    {
        this.authorized = authorized;
        if(authorized)
        {
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            msgPanel.setVisible(true);
            msgPanel.setManaged(true);
        }
        else
        {
            authPanel.setVisible(true);
            authPanel.setManaged(true);
            msgPanel.setVisible(false);
            msgPanel.setManaged(false);
        }
    }

    private Socket socket;

    private DataInputStream  in;
    private DataOutputStream out;

    @FXML
    private Button sendButton, connectBtn;

    @FXML
    private TextArea TextArea;

    @FXML
    private TextField TextField, loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    HBox authPanel, msgPanel;

    @FXML
    AnchorPane MainAnchorPane;



    public void sendMsg() {//послать сообщение на сервак

        try {
            String str = TextField.getText();
            out.writeUTF(str);//сообщение пошло на сервер
            TextField.clear();
            TextField.requestFocus();//фокус ввода на поле ввода сообщения
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    public void sendMsg(String msg) {//послать сообщение на сервак

        try
        {
            out.writeUTF(msg);//сообщение пошло на сервер
        }
        catch (IOException ex)
        {
            ex.getStackTrace();
        }
    }


        public void sendAuth() {//послать запрос на авторизацию в сторону сервера

        connect();
        sendMsg("/auth " + loginField.getText() + " " + passwordField.getText());
        loginField.clear();
        passwordField.clear();
    }

    public void connect() {
        if(socket==null || socket.isClosed())
        {
            try
            {

                socket = new Socket("localhost", 12345);
                in  = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());


                new Thread(new Runnable(){

                    @Override
                    public void run()
                    {
                        try {
                            while(true)
                            {
                                System.out.println("В цикле авторизации");

                                String str = in.readUTF();
                                if(str.equals("/authok"))
                                {
                                    setAthorized(true);
                                    isAuth = true;
                                    break;
                                }
                            }
                            while(true)
                            {
                                String str = in.readUTF();
                                TextArea.appendText(str);
                                TextArea.appendText("\n");
                            }
                        } catch (IOException ex) {
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

                            setAthorized(false);

                        }
                    }
                }).start();

            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setAthorized(false);

        new Thread(()->{
            long start = System.currentTimeMillis();
            while (!isAuth) {
                if (System.currentTimeMillis() - start > AUTH_TIMEOUT + 2000) {
                    System.out.println("Client not auth. Timeout exceeded.");
                    if(socket!=null) sendMsg("/end");
                    Platform.exit();
                    System.exit(0);
                    break;
                }
            }
        }).start();

        Platform.runLater(() -> {

                    ((Stage)MainAnchorPane.getScene().getWindow()).setOnCloseRequest(new EventHandler<WindowEvent>(){

                        @Override
                        public void handle(WindowEvent t)//здесь описываем то, что происходит при закрытии приложения
                        {
                            sendMsg("/end");
                            Platform.exit();
                            System.exit(0);
                        }
                    });
                }
        );

    }

}
