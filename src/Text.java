import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author segarci & albamig
 */
public class Text {

	private StringBuilder text;

	//Constructor que crea el objeto texto a partir de otro anterior
	public Text(StringBuilder oriText, int refi, int lenght){
		this.text = genText(oriText, refi, lenght);
	}

	public StringBuilder getText(){
		return text;
	}

	//Lee desde fichero y genera el texto con el contenido que hay en ��l
	public static StringBuilder readFile(String fileName){
		final String ERROR_FICHERO = "Error: Fichero no encontrado";
	    final String ROUTE = "texts/";
	    
	    File file;
	    FileReader fr = null;
	    BufferedReader br;
        
	    StringBuilder textBuilder = new StringBuilder();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            file = new File(ROUTE + fileName);

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
                textBuilder.append(linea);
            }

        }catch(Exception FileNotFoundException){
            System.out.println(ERROR_FICHERO);
            fileName = Main.writeString(Main.INTRODUCE_TEXTO);
            Text.readFile(fileName);

        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return textBuilder;
    }


	//genera un texto aleatorio a partir de los parametros que se le manda
	private StringBuilder genText(StringBuilder oriText, int refi, int lenght){

        StringBuilder strText = new StringBuilder();

        Date principio = new Date();
		HashMap<Character, WordHashMap> wordHashHashMap = WordHashMap.newInstance(oriText, refi);
        Date despues = new Date();

        System.out.println((double) (despues.getTime()-principio.getTime())/1000);

        //Pinta los distintos niveles de profundidad de la lista



        for (Map.Entry<Character, WordHashMap> entry : wordHashHashMap.entrySet())
        {
            System.out.println(entry.getKey()
                    + "  =  "
                    + entry.getValue()
                            .getPositionList().size()
            );
        }

        for (Map.Entry<Character, WordHashMap> entry : WordHashMap.getDefaultHashList().entrySet())
        {
            System.out.println(entry.getKey());
        }


        /*
        int a;
        while (lenght > 0 ){
            a = (int)(Math.random() * al.size());

            strText.append(al.get(a).getLetter());
            lenght--;
        }
        */

        //Provisional

		return strText;
	}

}
