import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCep{
    public Endereco buscaEndereco(String cep){
        URI endereco = URI.create("https://viacep.com.br/ws/" + cep.replace(" ", "+") + "/json/");
        System.out.println(endereco);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();
        try {
            HttpResponse<String>response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar o Cep: " +e.getMessage());
        }

    }
}
