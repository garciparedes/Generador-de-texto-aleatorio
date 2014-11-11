import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

		ArrayList<WordList> arrayList = WordList.newInstance(oriText,refi);


        //Pinta los distintos niveles de profundidad de la lista

        int rand;


        if (refi == 0){
            while (lenght > 0 ){
                rand = (int)(Math.random() * arrayList.size());

                strText.append(arrayList.get(rand).getLetter());
                lenght--;
            }
        } else {
            int i, valor, numLetters;

            while (lenght > 0 ){

                lenght = putChar(lenght, arrayList);
            }

        }

        //Provisional

        return strText;
    }

    private int putChar(int lenght, ArrayList<WordList> arrayList){
        int i, valor, numLetters, rand;

        try {
            numLetters = WordList.getArrayLenght(arrayList);


            if (lenght > 0 && numLetters != 0) {

                //numLetters = WordList.getArrayLenght(arrayList);
                rand = (int) (Math.random() * numLetters);
                i = 0;
                valor = 0;
                while (rand > valor) {
                    valor += arrayList.get(i).getPositions().size();
                    i++;
                }
                //strText.append(arrayList.get(i).getLetter());
                try {


                    System.out.print(arrayList.get(i).getLetter());
                    lenght = putChar(lenght - 1, arrayList.get(i).getWordLists());

                } catch (IndexOutOfBoundsException e){
                    
                }
                lenght--;

            }

        } catch (NullPointerException e){}

        return lenght;
    }

}