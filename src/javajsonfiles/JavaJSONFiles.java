package javajsonfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class JavaJSONFiles {

    public static String nomeArquivo = "dados.bin";
    
    public static void main(String[] args) {
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        
        File arquivo = new File(JavaJSONFiles.nomeArquivo);

        boolean exists = arquivo.exists();
        if (exists) {
            try {
                FileInputStream fis = new FileInputStream(JavaJSONFiles.nomeArquivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                listaProdutos = ( ArrayList<Produto>) ois.readObject();
                System.out.println("Serialized data is restored from Employee.ser file");
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Produto produtoNovo = new Produto();
        produtoNovo.nome = "Cebola";
        produtoNovo.quantidade = 123;
        produtoNovo.valor = 12.4f;

        listaProdutos.add(produtoNovo);
        
        for (Produto produto : listaProdutos) {
            System.out.println("Produto:" + produto.nome);
            
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(JavaJSONFiles.nomeArquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaProdutos);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
