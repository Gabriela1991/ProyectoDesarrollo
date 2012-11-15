/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grailsapplication1;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.Entry;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.RequestTokenPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.WebAuthSession;
import com.dropbox.client2.session.WebAuthSession.WebAuthInfo;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyla
 */
public class Dropbox {

    /**
     * @param args the command line arguments
     */
    final static private String APP_KEY = "s6c9l9rdxibv9rj";
    final static private String APP_SECRET = "oarccj4xfs7tckh";
    final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;
    static private DropboxAPI<WebAuthSession> mDBApi;
    public static AppKeyPair appKeys ;
    public static WebAuthSession session ;
    public static  WebAuthInfo authInfo ;
    
    public void d(File file){
        
     //    try{
               
        mDBApi = new DropboxAPI<WebAuthSession>(session);
        AccessTokenPair reAuthTokens = new AccessTokenPair(APP_KEY, APP_SECRET);
         DropboxAPI.Account account = mDBApi.accountInfo();
        System.out.println("User Name: " + account.displayName);    
            
        //String fileContents = "Bienvenido a Block de Notas!              "+
        //"Desarrollado por Keyla Hernandez, Maria Loreto, Angel Valderrama";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
        Entry newEntry = mDBApi.putFile("/"+file.getName()+"."+getExtension(file), inputStream, file.length(), null, null);
        
        
 //  }
//   catch (DropboxException ex)
//        {
//            System.out.println("fue al dropbox");
//        }


        
    }
  
    public static String getExtension(File f)
{
String ext = null;
String s = f.getName();
int i = s.lastIndexOf('.');

if (i > 0 && i < s.length() - 1)
ext = s.substring(i+1).toLowerCase();

if(ext == null)
return "";
return ext;
}
    
    
    
    
    public static void main(String[] args) throws DropboxException, IOException, URISyntaxException {
        // TODO code application logic here
       

   try{
         appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        session = new WebAuthSession(appKeys, ACCESS_TYPE);
        authInfo = session.getAuthInfo();

        RequestTokenPair pair = authInfo.requestTokenPair;
        //String url = authInfo.url;
        println(pair.key.toString());
        String url= "https://www.dropbox.com/0/oauth/authorize?oauth_token="+pair.key.toString()+"&oauth_callback=http://www.dropbox.com";
        Desktop.getDesktop().browse(new URL(url).toURI());
        JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");
        session.retrieveWebAccessToken(pair);

        AccessTokenPair tokens = session.getAccessTokenPair();
        
//        mDBApi = new DropboxAPI<WebAuthSession>(session);
//        
//         DropboxAPI.Account account = mDBApi.accountInfo();
//        System.out.println("User Name: " + account.displayName);    
//            
//        String fileContents = "Bienvenido a Block de Notas!              "+
//        "Desarrollado por Keyla Hernandez, Maria Loreto, Angel Valderrama";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileContents.getBytes());
//        Entry newEntry = mDBApi.putFile("/notebook.txt", inputStream, fileContents.length(), null, null);
//        
        
   }
   catch (DropboxException ex)
        {
            System.out.println("fue al dropbox");
        }


        
    }
    
}
