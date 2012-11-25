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
import javax.swing.JOptionPane
import java.awt.List;
import com.dropbox.client2.exception.DropboxServerException
import com.dropbox.client2.DropboxAPI.DropboxLink

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
    
  
    public static String auth(String keys) throws DropboxException, IOException, URISyntaxException {
        // TODO code application logic here
       

        try{
            appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
            session = new WebAuthSession(appKeys, ACCESS_TYPE);
            authInfo = session.getAuthInfo();
println(keys);
            if(keys==null){ // si persona no ha autorizado a dropbox. key y secret estan vacias
                RequestTokenPair pair = authInfo.requestTokenPair;
            
                println(pair.key.toString());
                String url= "https://www.dropbox.com/0/oauth/authorize?oauth_token="+pair.key.toString()+"&oauth_callback=http://www.dropbox.com";
                Desktop.getDesktop().browse(new URL(url).toURI());
                JOptionPane.showMessageDialog(null, "Press ok to continue once you have authenticated.");
                session.retrieveWebAccessToken(pair);
                AccessTokenPair tokens = session.getAccessTokenPair();
                println("key "+tokens.key)
                println("secret "+ tokens.secret)
                mDBApi = new DropboxAPI<WebAuthSession>(session);
                 DropboxAPI.Account account = mDBApi.accountInfo();
        System.out.println("User Name: " + account.displayName);
        println(tokens.key.toString()+"/"+tokens.secret.toString())
        return (tokens.key.toString()+"/"+tokens.secret.toString())
            }
            return null;
//            else // ya se ha autorizado a dropbox. key y secret no son vacias
//            {
//                  mDBApi = new DropboxAPI<WebAuthSession>(session);
//                AccessTokenPair reAuthTokens = new AccessTokenPair("zr1kvtzefrkf339", "f7y6rns0aw731ma");
//                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
//               
//              
//                System.out.println("Re-authentication Sucessful!");
//               println( mDBApi.accountInfo());
//            }
        }
        catch (DropboxException ex)
        {
            System.out.println("fue al dropbox");
        }
        
       catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
      
    }
    /**
     *@param 
     **/
      public String subirArchivo(File file, String key, String secret){
        
        try{
        mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
         
 
        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
        Entry newEntry = mDBApi.putFile("/"+file.getName(), inputStream, file.length(), null, null);
           
        return  (newEntry.fileName());    
        }
        catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
    }
    
    public void eliminarArchivo(String nombrefile,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
         
//        System.out.println("User Name: " + account.displayName); 
      
        Entry newEntry = mDBApi.delete("/"+nombrefile);
        } catch (DropboxServerException e) {
			System.out.println("El archivo no esta en dropbox con ese nombre");
		}
                 
        catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
    }
    
    public String buscarArchivo(String nombreadj,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
         
        System.out.println("User Name: " + nombreadj); 
        
            ArrayList  newEntry = (ArrayList) mDBApi.search("/",nombreadj,1,true);
            DropboxLink share= mDBApi.share("/"+nombreadj);
             
            share=mDBApi.media("/"+nombreadj, true);
                        
            return share.url
        } catch (DropboxServerException e) {
			System.out.println("El archivo no esta en dropbox con ese nombre");
		}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("El archivo no esta en dropbox");
            
                }
    }
}
