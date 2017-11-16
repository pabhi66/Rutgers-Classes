import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;

public class EchoServer {

    public static void main(String[] args) throws Exception {

        String client_message = null; //string to store the client message that is being passed to server
        String inverted_client_message = null; //string to store the inverted client message

        //Accept the echo server port number as args[0] in the main method. The port is then into an int.
        int port_number = Integer.parseInt(args[0]);

        // create socket using the given port number from the command line argument
        ServerSocket MyService = new ServerSocket(port_number);

        // a "blocking" call which waits until a connection is requested and accept the connection from the client
        Socket clientSocket = MyService.accept();


        // open up IO streams for the socket
        //gets the input from the client
        //We will create a BufferedReader for the input stream so we can use readLine to read data a line at a time
        BufferedReader input_from_client = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //We will create a DataOutputStream for the output stream so we can write bytes.
        //sends the output to the client
        DataOutputStream output_to_client = new DataOutputStream(clientSocket.getOutputStream());

        // loop as long as client does not enter '#' or "$" as a line of string client can enter a string and server
        //will send back the inverted string
        while ((client_message = input_from_client.readLine()) != null) {

            // close IO streams, then socket when the user input '#' or '$' as a line of string
            if(client_message.equals("#") || client_message.equals("$")){
                clientSocket.close(); //close client
                MyService.close(); //closer server
                input_from_client.close(); //close input stream
                output_to_client.close(); //close output stream
                break; //break the loop
            }

            //invert the string using string builder
            inverted_client_message = new StringBuilder(client_message).reverse().toString() + '\n';

            //print the client message un-inverted to the server side
            System.out.println(client_message);

            //send the inverted client message from the server side to the client side
            output_to_client.writeBytes(inverted_client_message);
        }
    }
}

