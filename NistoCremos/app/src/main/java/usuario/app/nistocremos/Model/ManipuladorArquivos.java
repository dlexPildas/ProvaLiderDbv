package usuario.app.nistocremos.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Dlex on 15/08/2017.
 */

public class ManipuladorArquivos {
    LinkedList lido = new LinkedList();

    public LinkedList leitor(String path) throws FileNotFoundException,IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = buffRead.readLine())!= null) {
                String c = (String)linha;
                lido.add(linha);
            }
        }
        return lido;
    }
}
