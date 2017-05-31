package cl.blueprintsit.framework.auth;

import cl.blueprintsit.utils.encryption.Encryption;
import cl.blueprintsit.utils.encryption.EncryptionException;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
@Stateless
@Interceptors(value={net.bull.javamelody.MonitoringInterceptor.class})
public class DummyAuthenticationMethod extends AuthenticationMethod {

    public void authenticate(String username, String password) throws AuthenticationException {

        byte[] encripted = {-1, 79, 127, -126, 64, -39, -99, -41, -48, 102, -87, 16, -89, -69, -14, 66};
        if("bpadmin".equals(username) && "palabritamagica".equals(decrypt(encripted,password)))
            return;

        byte[] encripted2 = {107,96,-104,-66,8,-2,-48,93,88,36,-117,-108,73,98,-66,-52};
        if("ejecutorml".equals(username) && "palabritamagica".equals(decrypt(encripted2,password)))
            return;

        throw new AuthenticationException("Nombre de usuario y/o Contraseña incorrecta");

    }



    public String decrypt(byte[] strEncrypted,String strKey) throws AuthenticationException {

        try {
            return Encryption.decrypt(strEncrypted,strKey);

        }catch (EncryptionException e){
            throw new AuthenticationException("Nombre de usuario y/o Contraseña incorrecta");
        }
    }

}
