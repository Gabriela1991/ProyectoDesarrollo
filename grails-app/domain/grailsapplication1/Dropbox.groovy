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
import com.dropbox.client2.exception.*
import java.net.UnknownHostException;
import java.rmi.UnknownHostException;
/**
 *
 * @author Keyla
 */
public class Dropbox {

    final static private String APP_KEY = "s6c9l9rdxibv9rj";
    final static private String APP_SECRET = "oarccj4xfs7tckh";
    final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;
    static private DropboxAPI<WebAuthSession> mDBApi;
    public static AppKeyPair appKeys ;
    public static WebAuthSession session ;
    public static  WebAuthInfo authInfo ;
    
  
    /**
     * Metodo encargado de verificar si el usuario ya ha dado permisos a la aplicacion para acceder a dropbox.
     * @param keys: atributo del usuario que guarda las llaves de dropbox
     * si keys es null, el usuario no ha autorizado. dropbox genera lac claves y son retornadas
     * si keys es distinto de null, el usuario ya ha permitido acceso. Por lo que el metodo retorna null
     * */
    public static String auth(String keys) throws DropboxException, IOException, URISyntaxException {
       
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
                JOptionPane.showMessageDialog(this, "Presione continuar cuando haya permitido el acceso a dropbox");
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
     * Metodo para almacenar un archivo en dropbox
     *@param file: archivo a subir
     *@param key: clave de acceso del usuario generada por dropbox
     *@param secret: clave de acceso del usuario generada por dropbox
     *@return nombre que uso dropbox para almacenar el archivo
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
            return null;
        }
    }
    
    /**
     * Metodo para eliminar un archivo de dropbox
     *@param nombrefile: nombre de archivo a eliminar
     *@param key: clave de acceso del usuario generada por dropbox
     *@param secret: clave de acceso del usuario generada por dropbox
     **/
    public void eliminarArchivo(String nombrefile,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
         
        Entry newEntry = mDBApi.delete("/"+nombrefile);
        } catch (DropboxServerException e) {
			System.out.println("El archivo no esta en dropbox con ese nombre");
		}
                 
        catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
    }
    
    /**
     * Metodo que busca archivo en dropbox y retorna el url de vista previa del archivo
     *@param nombreadj: nombre de archivo a buscar
     *@param key: clave de acceso del usuario generada por dropbox
     *@param secret: clave de acceso del usuario generada por dropbox
     *@return url de vista previa del archivo
     **/
    public String buscarArchivo(String nombreadj,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
       
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
                catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
    }
    
       /**
     * Metodo que busca archivo en dropbox y retorna el url de descarga del archivo
     *@param nombreadj: nombre de archivo a buscar
     *@param key: clave de acceso del usuario generada por dropbox
     *@param secret: clave de acceso del usuario generada por dropbox
     *@return url de vista previa del archivo
     **/
    public String descargarArchivo(String nombreadj,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
       
            ArrayList  newEntry = (ArrayList) mDBApi.search("/",nombreadj,1,true);
            DropboxLink share= mDBApi.share("/"+nombreadj);
                        
            return share.url
        } catch (DropboxServerException e) {
			System.out.println("El archivo no esta en dropbox con ese nombre");
		}
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("El archivo no esta en dropbox");
            
                }
                catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
        }
    }
}
