
package sedrsa;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;


import javax.crypto.Cipher;
/**
 *
 * @author ronaldvega
 */
public class SedRSA {

    
    public static void main(String[] args) throws NoSuchAlgorithmException, Exception {
        //Creamos un elemento de tipo KeyPair donde guardaremos las 
        //llaves que obtenemos de nuestra funcion ObtenerPar();
        
        KeyPair keyPair = ObtenerPar();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey privKey = keyPair.getPrivate();
        
        
        // encrypt the message
        String mensaje = "Este es un mensaje secreto";
        
        byte [] mencript = encriptar(privKey, mensaje);     
        System.out.print("Este es el mensaje encriptado en tipo byte: ");
        System.out.println(mencript);// <<encrypted message>>
        
        // decrypt the message
        byte[] secret = desencriptar(pubKey, mencript); 
        System.out.print("Este es el mensaje desencriptado: ");
        System.out.println(new String(secret)); // This is a secret message

    }
    
    public static KeyPair ObtenerPar() throws NoSuchAlgorithmException {
        //Primero asignamos el tamano (en bytes) de la LLave que vamos a utilizar.
        final int tamLlave = 2048;
        //Creamos un generador de pares de llaves y le mandamos como parametro
        //para su instancia el tipo de algoritmo que vamos a utilizar para encriptar y desencriptar.
        KeyPairGenerator generadorLlaves = KeyPairGenerator.getInstance("RSA");
        //Este generador se inicializa con el tamano de la llave que hemos definido.
        generadorLlaves.initialize(tamLlave);      
        return generadorLlaves.genKeyPair();
    }
    
    
    
    public static byte[] encriptar(PrivateKey privateKey, String message) throws Exception {
        //Creamos una instancia de Cipher y lo instanciamos de tipo RSA.
        Cipher cipher = Cipher.getInstance("RSA"); 
        //Se le define que modo va a utilizar.
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        //Esta funcion se utiliza cuando solo se va encriptar 1 solo bloque de texto.
        return cipher.doFinal(message.getBytes());  
    }
    
    public static byte[] desencriptar(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }
    
}
