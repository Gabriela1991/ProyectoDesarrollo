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
    final static private String APP_KEY1 = "leptv1sfus5ywu7";
    final static private String APP_SECRET1 = "ocph4agc0d3ktmw";
    final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;
    final static private AccessType acceso=AccessType.APP_FOLDER;
    static private DropboxAPI<WebAuthSession> mDBApi;
    public static AppKeyPair appKeys ;
    public static WebAuthSession session ;
    public static  WebAuthInfo authInfo ;
    
    public static AppKeyPair appKeys1 ;
  public static WebAuthSession session1 ;
   public static    WebAuthInfo authInfo1 ;
    
    /**
     *Autentificacion y creacion de un directorio en dropbox para el almacenamiento de la aplicacion
     *
     **/
    public static String unit(String keys){
        
        try{
            appKeys1 = new AppKeyPair("leptv1sfus5ywu7","ocph4agc0d3ktmw");
            session1 = new WebAuthSession(appKeys1, acceso);
            authInfo1 = session1.getAuthInfo();
            
            if(keys==null){ // si persona no ha autorizado a dropbox. key y secret estan vacias
                RequestTokenPair pair = authInfo1.requestTokenPair;
                String url= "https://www.dropbox.com/0/oauth/authorize?oauth_token="+pair.key.toString()+"&oauth_callback=https://www.dropbox.com/home/Apps";
                
                
                Desktop.getDesktop().browse(new URL(url).toURI());
                sleep(10000)
                //    JOptionPane.showMessageDialog(null, "Presione continuar cuando haya permitido el acceso a dropbox");
                session1.retrieveWebAccessToken(pair);
                AccessTokenPair tokens = session1.getAccessTokenPair();
               
                mDBApi = new DropboxAPI<WebAuthSession>(session1);
                 DropboxAPI.Account account = mDBApi.accountInfo();
        System.out.println("Nombre Usuario Dropbox: " + account.displayName);
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
             throw new DropboxException(e);
        }
      
        
        
    }
  
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
                String url= "https://www.dropbox.com/0/oauth/authorize?oauth_token="+pair.key.toString()+"&oauth_callback=http://www.dropbox.com";
                Desktop.getDesktop().browse(new URL(url).toURI());
                sleep(17000)
                //JOptionPane.showMessageDialog(null, "Presione continuar cuando haya permitido el acceso a dropbox");
                session.retrieveWebAccessToken(pair);
                AccessTokenPair tokens = session.getAccessTokenPair();
                println("key "+tokens.key)
                println("secret "+ tokens.secret)
                mDBApi = new DropboxAPI<WebAuthSession>(session);
                 DropboxAPI.Account account = mDBApi.accountInfo();
        System.out.println("Nombre Usuario Dropbox: " + account.displayName);
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
             throw new DropboxException(e);
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
                    Entry newEntry;
                    try {
                    newEntry = mDBApi.putFile("/"+file.getName(), inputStream, file.length(), null, null);
                    } catch (Exception e){
                        System.out.println("No hay conexion con internet/dropbox ");
                    return null;
                    }
                  
                return  (newEntry.fileName());    
        }
        catch (UnknownHostException e){
            System.out.println("No hay conexion con internet/dropbox");
            return null;
        } catch (IllegalArgumentException ee){
            return null;
        }
    }
    
    /**
     * Metodo para eliminar un archivo de dropbox
     *@param nombrefile: nombre de archivo a eliminar
     *@param key: clave de acceso del usuario generada por dropbox
     *@param secret: clave de acceso del usuario generada por dropbox
     **/
    public int eliminarArchivo(String nombrefile,String key, String secret){
        
       try { 
           mDBApi = new DropboxAPI<WebAuthSession>(session);
                AccessTokenPair reAuthTokens = new AccessTokenPair(key, secret);
                mDBApi.getSession().setAccessTokenPair(reAuthTokens);
                System.out.println("Re-authentication Sucessful!");
         
        Entry newEntry;
            
            try{
                newEntry= mDBApi.delete("/"+nombrefile);   
            } catch (Exception e){
                return 0;
            } 
            return 1;
            
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
       
            ArrayList  newEntry;
            
            try { 
                newEntry= (ArrayList) mDBApi.search("/",nombreadj,1,true);
            } catch (Exception e){
                return null
            }
            
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
       
            ArrayList  newEntry;
            
            try{
              newEntry= (ArrayList) mDBApi.search("/",nombreadj,1,true);
            } catch (Exception e){
              return null  
            }
            
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
