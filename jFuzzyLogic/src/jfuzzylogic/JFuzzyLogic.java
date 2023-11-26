/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jfuzzylogic;
import net.sourceforge.jFuzzyLogic.FIS;

/*
INTEGRANTES
    VILLEGAS SOLIS JOSE MANUEL
    RIOS SICAIROS ANDREA
*/
public class JFuzzyLogic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "jFuzzyLogic/src/jfuzzylogic/controlVelocidad.fcl";
        FIS fis = FIS.load(fileName, true);
        
        if(fis == null){
            System.out.println("No se encontró el archivo: '" + fileName + "'");
            return;
        }
        
        fis.setVariable("Error", 0.18);
        fis.setVariable("Cambio_Error", 0.70);
        
        fis.evaluate();
        
        double control = fis.getVariable("Control").getValue();
        
        System.out.println("El control del motor es: " + control);
        

    }
    
}
