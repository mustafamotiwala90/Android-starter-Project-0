package com.mustafa.finaldistributed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinalchatActivity extends Activity {
	/** Called when the activity is first created. */
	TextView statusmsg;
	Button connect;
	Button testpurpose;
	EditText editwindow;
	String msg;
	String messg;
	String phonenumber;
	TelephonyManager teleman;
	ServerSocket servsock = null;
	String Ipaddress = "10.0.2.2";
	final static int portnum1 = 5050;
	final static int portnum2 = 6060;
	Handler handler = new Handler();
	
	
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		statusmsg = (TextView) findViewById(R.id.text1);
		connect = (Button) findViewById(R.id.sendbutton);
		testpurpose = (Button) findViewById(R.id.button1);
		editwindow = (EditText) findViewById(R.id.edit1);


		connect.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread clientthread = new Thread(new ClientThr(), "Client Thread");
				clientthread.start();
				}
			});
		
		Thread serverthread = new Thread(new ServerThr(), "Server Thread");
		serverthread.start();
	}

	/*used from the android developer's website ..i.e the handler */
		Runnable myHandler = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			try{
				statusmsg.append(msg);
				statusmsg.append("\n");
				Log.d("message","reached handler");
				}catch(Exception e){
				e.printStackTrace();
			}
		}
	};

	Socket cliesock=null;
	
	
	class ServerThr implements Runnable {
		
	static final int server = 8888;

		public void run() {
			// TODO Auto-generated method stub
			try {
				servsock = new ServerSocket(server);
				synchronized(servsock)
				{
					Log.d("server listening ", "waiting");
					servsock.wait(1000*10);
					while(true)
					{			
						//if(cliesock==null) 
						//{
						Log.d("client","before creating socket");
						cliesock = servsock.accept();
						if(cliesock!=null)
						{
							Log.d("accepted", "server accepted");
							//handler.post(myHandler);
							Log.d("message","i reached here" + cliesock);
							BufferedReader clientdata = new BufferedReader(new InputStreamReader(cliesock.getInputStream()));
							Log.d("Reading","Reading the data");
							msg= clientdata.readLine();
							handler.post(myHandler);
							Log.d("message","did the message reach " + msg); 
							
						}			
						//}
					}
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	class ClientThr implements Runnable {

		public void run() {
			// TODO Auto-generated method stub
			try {
				String emulator5554 = "15555215554";
				String emulator5556 = "15555215556";
				Socket socket = null;
				DataOutputStream dos = null;
				DataInputStream dis = null;
				teleman = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				phonenumber = teleman.getLine1Number();
				// we have to select one out of the two emulators and make them
				// the client . Earlier we saw that on the event
				// of a button click the particular emulator becomes the client
				// while other becomes the server so now we have to
				// create a new client socket and bind that socket to the port
				// identified by the emulator's unique phone number which was
				// found by accesssing the about phone settings'
				if (phonenumber.equals(emulator5554)) {
					socket = new Socket("10.0.2.2",portnum1);
					Log.d("Socket","socket of emulator 5554 is connected at");
				} else if (phonenumber.equals(emulator5556)) {
					socket = new Socket("10.0.2.2",portnum2);
					Log.d("Socket","socket of emulator 5554 is connected at");
				} else {
					Log.d("Client", "problem in making socket");
				}
				//socket.close();
				messg = editwindow.getText().toString();
				PrintWriter out;
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
				out.println(messg);
				Log.d("Client", "Client sent message" +out);
			} catch (Exception r) {
				r.printStackTrace();
			}
		}

	}

	/*@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 try {
			  servsock.close();
			 } catch (IOException e) {
			 e.printStackTrace();
			 }
	} */
}