import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;
        String estado = "", cidade = "", endereco = "";

        while (!(opcao == 3)) {
            System.out.println("""
                    Digite o número da Opção:
                    1 - Buscar por Cep
                    2 - Buscar por Endereço
                    3 - Sair
                    """);

            opcao = leitura.nextInt();
            String cep = "";
            leitura.nextLine();

            if (opcao == 1) {
                System.out.println("Digite o cep: ");
                cep = leitura.nextLine();                
            } else if (opcao == 2) {
                System.out.println("Digite sua UF. Ex: SP");
                estado = leitura.nextLine();
                System.out.println("Digite sua cidade: ");
                cidade = leitura.nextLine();
                System.out.println("Digite o nome da rua: ");
                endereco = leitura.nextLine();
                cep = estado + "/" + cidade + "/" + endereco;
            } else if (opcao == 3) {
                break;
            }

            ConsultaCep consultaCep = new ConsultaCep();
            try {
                Endereco novoEndereco = consultaCep.buscaEndereco(cep);
                System.out.println(novoEndereco);
                GeradoDeArquivo gerador = new GeradoDeArquivo();
                gerador.salvaJson(novoEndereco);
            }catch(RuntimeException | IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
